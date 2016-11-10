package fi.johannes.sort;

import java.util.Comparator;

import fi.johannes.entity.Todo;

public class TodoCreatedComparator implements Comparator<Todo> {

	public TodoCreatedComparator() {
	}
	@Override
	public int compare(Todo o1, Todo o2) {
		return o1.getCreated().compareTo(o2.getCreated());
	}

}
