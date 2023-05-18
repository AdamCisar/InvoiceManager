package invoice.parser;

import java.io.IOException;
import java.util.LinkedList;

public interface Parser {

	void parse(String line, LinkedList<LinkedList<String>> searchTextArr, int numberOfPages) throws IOException;
}
