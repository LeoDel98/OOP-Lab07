package it.unibo.oop.lab.nesting2;

import java.util.Iterator;
import java.util.List;

public class OneListAcceptable<T> implements Acceptable<T> {
	
	private final List<T> myList;
	
	public OneListAcceptable(final List<T> list) {
		this.myList = list;
	}

	@Override
	public Acceptor<T> acceptor() {
		final Iterator<T> iterator = myList.iterator();
		return new Acceptor<T>() {

			@Override
			public void accept(T newElement) throws ElementNotAcceptedException {
				try {
					if (newElement.equals(iterator.next())) {
						return;
					}
				} catch (final Exception e) {
					System.out.println("No more elements");
				}
				
				throw new ElementNotAcceptedException(newElement);
			}

			@Override
			public void end() throws EndNotAcceptedException {
				try {
					if (!iterator.hasNext()) {
						return;
					}
				} catch (final Exception e) {
					System.out.println(e.getMessage());
				}
				
				throw new EndNotAcceptedException();
			}			
		};
	}

}
