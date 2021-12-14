package com.github.anjeyy.adventcode.year2021.six;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

class MainMain {

    private static final String WHITESPACE = " ";
    private static final Graph GRAPH = new Graph();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String rawTestCaseCount = in.nextLine().trim();
        int inputSize = Integer.parseInt(rawTestCaseCount);
        String[] input = new String[inputSize];
        String inputItem;
        for (int i = 0; i < inputSize; i++) {
            try {
                inputItem = in.nextLine();
            } catch (Exception e) {
                inputItem = null;
            }
            input[i] = inputItem;
        }
        doIt(input);
    }

    static void doIt(String[] input) {
        List<Command> commands = createCommands(input);
        processDependencies(commands);
        processOtherCommands(commands);
    }

    private static List<Command> createCommands(String[] input) {
        return Arrays.stream(input)
                     .map(Command::from)
                     .collect(Collectors.toList());
    }

    private static void processDependencies(List<Command> commands) {
        for (Command currentCommand : commands) {
            if (currentCommand.getOperation() == Operation.DEPEND) {
                System.out.println(currentCommand);
                GRAPH.addDependency(currentCommand);
            }
        }
    }

    private static void processOtherCommands(List<Command> commands) {
        for (Command currentCommand : commands) {
            Operation currentOperation = currentCommand.getOperation();
            if (currentOperation != Operation.DEPEND) {
                System.out.println(currentCommand);
            }
            if (currentOperation == Operation.INSTALL) {
                Stack<Item> installOrder = GRAPH.installOrder(currentCommand.getComponent());
                for (Item currentItem : installOrder) {
                    System.out.println("Installing " + currentItem.getName());
                }
            } else if (currentOperation == Operation.LIST) {
                GRAPH.currentlyInstalled()
                     .forEach(item -> System.out.println(item.getName()));
            } else if (currentOperation == Operation.REMOVE) {
                //todo remove + cyclic check

            } else if (currentOperation == Operation.END) {
                System.exit(1);
            }
        }

    }

    private static class Graph {

        private final Map<Item, Set<Item>> dependencies = new HashMap<>();

        boolean addDependency(Command command) {
            Item item = command.getComponent();
            if (item == null || isCyclic(command)) {
                return false;
            }
            Set<Item> dependsOn = command.getDependencies();
            dependencies.put(item, dependsOn);
            return true;
        }

        private boolean isCyclic(Command command) {
            Item necessaryUniqueComponent = command.getComponent();
            Queue<Item> toBeChecked = new ArrayDeque<>(command.getDependencies());

            while (!toBeChecked.isEmpty()) {
                Item item = toBeChecked.poll();
                Set<Item> itemDependencies = dependencies.get(item);
                if (itemDependencies != null && itemDependencies.contains(necessaryUniqueComponent)) {
                    System.out.printf(
                        "%s depends on %s, ignoring command%n",
                        item.getName(),
                        necessaryUniqueComponent.getName()
                    );
                    return true;
                }
            }
            return false;
        }

        Stack<Item> installOrder(Item item) {
            Stack<Item> result = new Stack<>();
            Set<Item> dependsOn = dependencies.get(item);
            Queue<Item> searchInstalled = new ArrayDeque<>(dependsOn == null ? new HashSet<>() : dependsOn);
            result.push(item);
            item.install();
            while (!searchInstalled.isEmpty()) {
                Item currentItem = searchInstalled.poll();
                Set<Item> currentDependsOn =
                    dependencies.get(currentItem) == null ? new HashSet<>() : dependencies.get(currentItem);
                if (!currentDependsOn.isEmpty()) {
                    searchInstalled.addAll(currentDependsOn);
                }
                if (currentItem.isNotInstalled()) {
                    result.push(currentItem);
                    currentItem.install();
                }
            }
            return result;
        }

        Set<Item> currentlyInstalled() {
            Set<Item> result = new HashSet<>();
            Set<Item> components = dependencies.keySet().stream().filter(Item::isInstalled).collect(Collectors.toSet());
            Set<Item> dependsOn = dependencies.values()
                                              .stream()
                                              .flatMap(Collection::stream)
                                              .filter(Item::isInstalled)
                                              .collect(Collectors.toSet());
            result.addAll(components);
            result.addAll(dependsOn);
            return result;
        }
    }

    private static class Item {

        private final String name;
        private boolean installed;

        private Item(String name) {
            this.name = verify(name);
        }

        private static String verify(String name) {
            String nullSafeName = Objects.requireNonNull(name, "No name given for item.");
            if (nullSafeName.length() > 10) {
                throw new IllegalArgumentException("Item name exceeds character length of 10.");
            }
            return nullSafeName;
        }

        static Item of(String name) {
            return new Item(name);
        }

        String getName() {
            return name;
        }

        boolean isNotInstalled() {
            return !isInstalled();
        }

        boolean isInstalled() {
            return installed;
        }

        void install() {
            this.installed = true;
        }

        void uninstall() {
            this.installed = false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Item)) {
                return false;
            }
            Item item = (Item) o;
            return name.equals(item.name);  //case sensitive
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "Item{" + name + "}";
        }
    }

    private static class Command {

        private final Operation operation;
        private final List<Item> items;

        private Command(String rawCommand) {
            this.operation = extractOperation(rawCommand);
            this.items = extractArguments(rawCommand);
        }

        private static Operation extractOperation(String rawCommand) {
            String rawOperation = rawCommand.split(WHITESPACE)[0].trim();
            return Operation.valueOf(rawOperation);
        }

        private static List<Item> extractArguments(String rawCommand) {
            String[] rawArguments = rawCommand.split(WHITESPACE);
            List<Item> result = new ArrayList<>();
            // starts with '1' to exclude operation
            for (int i = 1; i < rawArguments.length; i++) {
                if (!rawArguments[i].equals("") || !rawArguments[i].equals(WHITESPACE)) {
                    Item argument = Item.of(rawArguments[i].trim());
                    result.add(argument);
                }
            }
            return result;
        }

        static Command from(String rawCommand) {
            return new Command(rawCommand);
        }

        Operation getOperation() {
            return operation;
        }

        Item getComponent() {
            return items.stream().findFirst().orElse(null);
        }

        Set<Item> getDependencies() {
            Set<Item> result = new HashSet<>();
            for (int i = 1; i < items.size(); i++) {
                Item currentItem = items.get(i);
                result.add(currentItem);
            }
            return result;
        }

        @Override
        public String toString() {
            return operation + WHITESPACE + items.stream().map(Item::getName).collect(Collectors.joining(WHITESPACE));
        }
    }

    private enum Operation {
        DEPEND("DEPEND"),
        INSTALL("INSTALL"),
        REMOVE("REMOVE"),
        LIST("LIST"),
        END("END");

        private final String command;

        Operation(String command) {
            this.command = command;
        }

        String asString() {
            return command;
        }
    }
}
