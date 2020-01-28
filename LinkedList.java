import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The LinkedList class describes a doubly-linked list
 * that supports forward and reverse iteration.
 *
 * Elements of this list are of type E and each element is a node with a value
 * and references to the preceding and succeeding nodes.
 * This class offers methods to access and manipulate the list and its elements,
 * as well as inner classes to support iteration and create the nodes of which this
 * list is built with.
 *
 * @param <E> - the type of elements held in this collection.
 * @author ereimer18georgefox.edu
 */
public class LinkedList<E> implements Iterable<E> {

    private static final int EMPTY = 0;
    private static final int NOT_FOUND = -1;

    private LinkedListNode<E> _head;
    private LinkedListNode<E> _tail;
    private int _size;


    /**
     * Constructs an instance of a LinkedList.
     *
     * Constructs an empty instance of a LinkedList.
     * The size is initialized as 0 and the head and tail nodes
     * are initialized as null with null references.
     */
    public LinkedList() {
        _head = new LinkedListNode<>();
        _tail = new LinkedListNode<>();
        _size = 0;
    }


    /**
     * Inserts the specified element at the specified position in this list.
     *
     * Adds the element as a new node to the list with references to the nodes before and after it.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right.
     *
     * @param index - index at which the specified element is to be inserted.
     * @param element - element to be inserted.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size()).
     */
    public void add(int index, E element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        else {
            LinkedListNode<E> currentNode = traverse(index);
            LinkedListNode<E> previous = currentNode.getPrevious();
            LinkedListNode<E> newNode = new LinkedListNode<>(element, previous, currentNode);
            previous.setNext(newNode);
            currentNode.setPrevious(newNode);
        }
        _size++;
    }


    /**
     * Appends the specified element to the end of this list.
     *
     * Adds the element as a new node to the end of the list.
     * The tail is updated to reference this element.
     * The element references null as the next element.
     *
     * @param element - element to be appended to this list.
     * @return if the append was successful.
     */
    public boolean add(E element) {
        boolean successful = true;
        int index = size();
        if (index < 0) {
            successful = false;
        }
        else if (successful) {
            if (index == EMPTY) {
                _head.setValue(element);
            }
            else if (index == EMPTY + 1) {
                _tail.setValue(element);
                _tail.setPrevious(_head);
                _head.setNext(_tail);
            }
            else {
                LinkedListNode<E> newNode = new LinkedListNode<>(element);
                _tail.setNext(newNode);
                newNode.setPrevious(_tail);
                _tail = newNode;
            }
            _size++;
        }
        return successful;
    }


    /**
     * Removes all of the elements from this list.
     *
     * The head and tail elements are set to null and release references to inner elements.
     * The rest of the elements are left to be recycled after references are set to null.
     * The list will be empty after this call returns.
     */
    public void clear() {
        _head.setNext(null);
        _head.setValue(null);
        _tail.setPrevious(null);
        _tail.setValue(null);
        _size = EMPTY;
    }


    /**
     * Returns the element at the specified position in this list.
     *
     * Traverses through the list to find the element at the specified index.
     * An error is thrown if the target index is out of range for the list.
     *
     * @param index - index of the element to return.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException - if the index is out of range
     * (index < 0 || index >= size()).
     */
    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return traverse(index).getValue();
    }


    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     *
     * Traverses forwards through the list until the first occurrence of the element is found,
     * or to the end if the list does not contain the element.
     * Returns the index of the element, or -1 if the element is not found.
     *
     * @param element - element to search for.
     * @return the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     */
    public int indexOf(E element) {
        boolean found = false;
        int index = EMPTY;
        LinkedListNode<E> currentNode = _head;
        while (!found && index < size()) {
            if (currentNode.getValue() == element) {
                found = true;
            }
            else {
                currentNode = currentNode.getNext();
                index++;
            }
        }
        if (!found) {
            index = NOT_FOUND;
        }

        return index;
    }


    /**
     * Returns true if this list contains no elements.
     *
     * Checks the size of this list, which is automatically updated as the list changes.
     * If the list is empty, this method returns true.
     *
     * @return if this list contains no elements.
     */
    public boolean isEmpty() {
        return size() <= EMPTY;
    }


    /**
     * Removes the element at the specified position in this list.
     *
     * Traverses through list to index and removes the element at that position.
     * Shifts any subsequent elements to the left.
     * Returns the element that was removed from the list.
     * An error is thrown if the target index is out of the range of this list.
     *
     * @param index - the index of the element to be removed.
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException - if the index is out of range
     * (index < 0 || index >= size()).
     */
    public E remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        LinkedListNode<E> currentNode = traverse(index);
        LinkedListNode<E> nextNode = currentNode.getNext();
        LinkedListNode<E> previousNode = currentNode.getPrevious();
        E element = currentNode.getValue();

        nextNode.setPrevious(previousNode);
        previousNode.setNext(nextNode);
        _size--;
        return element;
    }


    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * Traverses through list to the specified index.
     * Replaces the existing element at that position with the specified element.
     * The existing element is returned.
     * An error is thrown if the target index is out of the range of this list.
     *
     * @param index - index of the element to replace.
     * @param element - element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException - if the index is out of range
     * (index < 0 || index >= size()).
     */
    public E set(int index, E element) {
        LinkedListNode<E> currentNode = traverse(index);
        currentNode.setValue(element);
        return null;
    }


    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list.
     */
    public int size() {
        return _size;
    }


    /**
     * Returns an iterator over elements of type T.
     *
     * Accesses the inner class LinkedListIterator<E> and constructs an instance
     * using a reference to this LinkedList as the parameter.
     * Sets the boolean parameter to false in order to iterate forwards from the head.
     *
     * @return an Iterator.
     */
    public Iterator<E> iterator() {
        return new LinkedListIterator<>(this, false);
    }


    /**
     * Returns an iterator over elements of type T.
     *
     * Accesses the inner class LinkedListIterator<E> and constructs an instance
     * using a reference to this LinkedList as the parameter.
     * Sets the boolean parameter to true in order to iterate backwards from the tail.
     *
     * @return an Iterator.
     */
    public Iterator<E> reverseIterator() {
        return new LinkedListIterator<>(this, true);
    }


    /**
     * Returns the LinkedListNode at the specified index.
     *
     * Traverses through this list to the specified index.
     * Depending on the shortest path to the index,
     * this method will either traverse forwards from the head
     * or backwards from the tail.
     * An error is thrown if the target index is out of the range of this list.
     *
     * @param index - index of the LinkedListNode to be returned.
     * @return the LinkedListNode at the specified position.
     * @throws IndexOutOfBoundsException - if the index is out of range
     * (index < 0 || index >= size()).
     */
    private LinkedListNode<E> traverse(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        LinkedListNode<E> currentNode;
        if (size() - index >= size() / 2) {
            currentNode = _head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
        }
        else {
            currentNode = _tail;
            for (int i = size() - 1; i > index; i--) {
                currentNode = currentNode.getPrevious();
            }
        }
        return currentNode;
    }


    /**
     * This inner class describes the nodes of which a LinkedList consists of.
     * Each node has a value of type E as well as a reference to the LinkedListNode
     * preceding this node and the node succeeding this node in the list.
     * @param <E> - the type of value this node contains.
     */
    private class LinkedListNode<E> {
        private E _value;
        private LinkedListNode<E> _prev;
        private LinkedListNode<E> _next;


        /**
         * Creates an instance of a LinkedListNode.
         *
         * Creates an empty instance of a LinkedListNode of type E.
         * The value of this node is an element E and the node
         * contains references to the next and previous nodes in the list.
         * The value and references are set to null.
         */
        private LinkedListNode() {
            _value = null;
            _prev = null;
            _next = null;
        }

        /**
         * Creates an instance of a LinkedListNode.
         *
         * Creates an instance of a LinkedListNode of type E.
         * The value of this node is an element E.
         * The node contains references to the next and previous nodes in the list.
         * The value is specified and the references are set to null.
         *
         * @param value - the specified value of this node.
         */
        private LinkedListNode(E value) {
            _value = value;
            _prev = null;
            _next = null;
        }


        /**
         * Creates an instance of a LinkedListNode.
         *
         * Creates an instance of a LinkedListNode of type E.
         * The value of this node is an element E.
         * The node contains references to the next and previous nodes in the list.
         * The value and references are specified in the parameters.
         *
         * @param value - the specified value of this node.
         * @param prev - a reference to the preceding node in this list.
         * @param next - a reference to the succeeding node in this list.
         */
        private LinkedListNode(E value, LinkedListNode<E> prev, LinkedListNode<E> next) {
            _value = value;
            _prev = prev;
            _next = next;
        }


        /**
         * Returns the value of this node.
         *
         * Returns the value of type E for this node.
         *
         * @return - the value of this node.
         */
        public E getValue() {
            return _value;
        }


        /**
         * Returns the previous node in this list.
         *
         * Returns a reference to the node preceding this node
         * in this LinkedList.
         *
         * @return the previous node in this list.
         */
        public LinkedListNode<E> getPrevious() {
            return _prev;
        }


        /**
         * Returns the next node in this list.
         *
         * Returns a reference to the node succeeding this node
         * in this LinkedList.
         *
         * @return the next node in this list.
         */
        public LinkedListNode<E> getNext() {
            return _next;
        }


        /**
         * Sets the value of this list to the specified value.
         *
         * @param value - the specified value to set this node's value to.
         */
        public void setValue(E value) {
            _value = value;
        }


        /**
         * Sets this node's reference to the preceding node to the specified reference.
         *
         * @param prev - a reference to a preceding node in this list.
         */
        public void setPrevious(LinkedListNode<E> prev) {
            _prev = prev;
        }


        /**
         * Sets this node's reference to the succeeding node to the specified reference.
         *
         * @param next - a reference to a succeeding node in this list.
         */
        public void setNext(LinkedListNode<E> next) {
            _next = next;
        }
    }


    /**
     * LinkedListIterator is an inner class of LinkedList
     * and an implementation of the Iterator interface.
     *
     * This Iterator supports both forward and reverse iteration.
     * Methods are provided to iterate over the referenced list.
     * These methods include validating that iteration
     * can be continued over a list of elements,
     * and performing such iteration operations.
     * The class and constructor are private so that
     * the iterator can only be created from an existing
     * LinkedList.
     *
     * @param <E> - the type of elements to iterate over.
     */
    private class LinkedListIterator<E> implements Iterator<E> {

        private int _index;
        private LinkedList<E> _linkedList;
        private boolean _reverse;


        /**
         * Creates an instance of an ArrayListIterator.
         *
         * This constructor is automatically called when the iterator()
         * method of the ArrayList class is used to create an iterator.
         * The initial position is at the head if reverse is false,
         * and the tail if reverse is true.
         * This supports both forward and reverse iteration.
         *
         * @param linkedList - a reference to this LinkedList.
         * @param reverse - true if the LinkedListIterator is supposed to reverse iterate.
         */
        private LinkedListIterator(LinkedList<E> linkedList, boolean reverse) {

            _linkedList = linkedList;
            _reverse = reverse;

            if (reverse) {
                _index = (_linkedList.size() - 1);
            }
            else {
                _index = 0;
            }
        }


        /**
         * Returns true if the iteration has more elements.
         *
         * Returns true if next() would return the next element in a list
         * rather than throwing an exception.
         *
         * @return true if the iteration has more elements.
         */
        public boolean hasNext() {

            boolean isTrue = false;
            if (_reverse) {
                if (_index >= EMPTY) {
                    isTrue = true;
                }
            }
            else {
                if (_index < _linkedList.size()) {
                    isTrue = true;
                }
            }
            return isTrue;
        }


        /**
         * Returns the next element in the iteration.
         *
         * Returns the next element of the list being iterated over.
         * A NoSuchElementException is thrown if this method is called
         * on a list with no more elements to iterate over.
         *
         * @return the next element in the iteration.
         * @throws NoSuchElementException if the iteration has no more elements.
         */
        public E next() {
            E element;
            if (_reverse) {
                if (_index < EMPTY) {
                    throw new NoSuchElementException();
                }
                element = _linkedList.get(_index);
                _index--;
            }
            else {
                if (_index > _linkedList.size()) {
                    throw new NoSuchElementException();
                }
                element = _linkedList.get(_index);
                _index++;
            }

            return element;

        }

    }
}

