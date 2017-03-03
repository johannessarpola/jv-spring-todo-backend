package fi.johannes.sort;

import java.util.Comparator;

import fi.johannes.models.Todo;

public class TodoDeadlineComparator implements Comparator<Todo> {

	public TodoDeadlineComparator() {
	}
	@Override
	public int compare(Todo o1, Todo o2) {
		return o1.getDeadline().compareTo(o2.getDeadline());
	}

}
