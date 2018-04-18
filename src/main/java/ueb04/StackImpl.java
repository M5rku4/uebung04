package ueb04;

import java.util.Iterator;
import java.util.NoSuchElementException;

class StackImpl<T> implements Stack<T> {
	/**
	 * Gibt einen Reverse-Iterator zurück, d.h. ein Iterator, welcher die Elemente
	 * in umgekehrter Reihenfolge zurück gibt. Also als erstes das letzte (also "unterste"),
	 * Element, dann das zweit-unterste etc.
	 */
	class MyIterator implements Iterator<T>{
		Stack<Element> agenda = new StackImpl<>();

		public MyIterator() {
			if(top != null)
				agenda.push(top);
			Element e = top;
			while(e.next != null) {
				agenda.push(e.next);
				e = e.next;
			}
		}

		@Override
		public boolean hasNext() {
			return agenda.size() > 0;
		}

		@Override
		public T next() {
			return agenda.pop().value;
		}
	}
	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}

	private class Element {
		T value;
		Element next;
		Element(T value, Element next) {
			this.value = value;
			this.next = next;
		}
	}

	private Element top;
	private int s = 0;

	@Override
	public void push(T c) {
		top = new Element(c, top);
		s++;
	}

	@Override
	public T pop() {
		if (top == null)
			throw new NoSuchElementException();
		T v = top.value;
		top = top.next;
		s--;
		return v;
	}

	@Override
	public int size() {
		return s;
	}
}
