package cs143;

import java.util.Iterator;

/**
 * This data class represents an ordered collection in an endless circular list.
 *
 * @param <E> the type of values stored in the list
 */
public class EndlessList<E> implements Iterable<E> {

    //fields
    private Node<E> cursor;

    /**
     * adds the value right before the current oneand moves the cursor to the
     * new node, or if cursor is null make the value the cursor
     *
     * @param value value to be added
     */
    public void addPrev(E value) {
        if (cursor == null) {
            cursor = new Node<E>(value);
            cursor.setNext(cursor);
            cursor.setPrev(cursor);
        } else {
            Node newNode = new Node<E>(value, cursor.getPrev(), cursor);
            cursor.getPrev().setNext(newNode);
            cursor.setPrev(newNode);
            cursor = newNode;
        }
    }

    /**
     * adds the value right after the current one and moves the cursor to the
     * new node, or if the cursor is null make the value the cursor
     *
     * @param value value to be added
     */
    public void addNext(E value) {
        if (cursor == null) {
            cursor = new Node<E>(value);
            cursor.setNext(cursor);
            cursor.setPrev(cursor);
        } else {
            Node newNode = new Node<E>(value, cursor, cursor.getNext());
            cursor.getNext().setPrev(newNode);
            cursor.setNext(newNode);
            cursor = newNode;
        }
    }

    /**
     * removes the current value and returns it, moving the cursor to the next
     * node or setting it to null if the last value was removed
     *
     * @return the value be removed
     */
    public E remove() {
        Node<E> temp = cursor;
        Node<E> prevNode = cursor.getPrev();
        Node<E> nextNode = cursor.getNext();
        if (cursor != prevNode) {
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
            cursor = nextNode;
        } else {
            cursor = null;
        }
        return temp.getValue();
    }

    /**
     * returns the current value but does not remove it
     *
     * @return the current value
     */
    public E getValue() {
        return cursor.getValue();
    }

    /**
     * changes the current value to the given one, returning true if successful
     * and false if not(if currentNode is null)
     *
     * @param value the value to be set
     * @return true if the value is set
     */
    public boolean setValue(E value) {
        if (cursor == null) {
            return false;
        }
        cursor.setValue(value);
        return true;
    }

    /**
     * moves the cursor back one node and returns that value
     *
     * @return the pervious value
     */
    public E getPrev() {
        if (cursor == null) {
            return null;
        }
        cursor = cursor.getPrev();
        return cursor.getValue();
    }

    /**
     * moves the cursor forward one node and returns that value
     *
     * @return the next value
     */
    public E getNext() {
        if (cursor == null) {
            return null;
        }
        cursor = cursor.getNext();
        return cursor.getValue();
    }

    /**
     * moves the cursor forward until it finds the value or circles back to the
     * same place, returning true if the value was found, including at the
     * current node,or false if not
     *
     * @param value the value to be found
     * @return true if the value is found
     */
    public boolean moveToNext(E value) {
        if (cursor == null) {
            return false;
        }
        Node<E> currNode = cursor.getNext();
        do {
            if (currNode.getValue() == value) {
                cursor = currNode;
                return true;
            } else {
                currNode = currNode.getNext();
            }
        } while (currNode != cursor);
        return false;
    }

    /**
     * moves the cursor backwards until it finds the value or circles back to
     * the same place, returning true if the value was found, including at the
     * current node,or false if not
     *
     * @param value the value to be found
     * @return true if the value is found
     */
    public boolean moveToPrev(E value) {
        if (cursor == null) {
            return false;
        }
        Node<E> currNode = cursor.getPrev();
        do {
            if (currNode.getValue() == value) {
                cursor = currNode;
                return true;
            } else {
                currNode = currNode.getPrev();
            }
        } while (currNode != cursor);
        return false;
    }

    /**
     *
     */
    @Override
    public Iterator<E> iterator() {
        return new EndlessListIterator();

    }

    /**
     * Private class used to create an EndlessList iterator.
     */
    private class EndlessListIterator implements Iterator<E> {

        //fields
        private Node<E> firstNode = cursor;
        private boolean handledStart = false;

        /**
         * Returns true if the iteration has more elements.
         */
        @Override
        public boolean hasNext() {
            if (firstNode == null) {
                return false;
            }
            return (handledStart == false);
        }

        /**
         * Returns the current element move to next in the iteration.
         */
        @Override
        public E next() {
            E e = firstNode.getValue();
            firstNode = firstNode.getNext();
            if (firstNode == cursor) {
                handledStart = true;
            }
            return e;
        }

        /**
         * Removes from the underlying collection the last element returned by
         * this iterator.
         */
        @Override
        public void remove() {
            firstNode = firstNode.getPrev();
            Node<E> prevNode = firstNode.getPrev();
            Node<E> nextNode = firstNode.getNext();
            if (firstNode != prevNode) {
                prevNode.setNext(nextNode);
                nextNode.setPrev(prevNode);
                firstNode = nextNode;
            } else {
                firstNode = null;
            }
            cursor = firstNode;
        }
    }

}
