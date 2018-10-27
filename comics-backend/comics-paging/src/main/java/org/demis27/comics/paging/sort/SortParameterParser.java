package org.demis27.comics.paging.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;

@Service("sortParameterParser")
public class SortParameterParser {

    public static List<SortParameterElement> parse(String value) {
        if (value == null || value.length() == 0) {
            return Collections.emptyList();
        } else {
            List<SortParameterElement> sorts = new ArrayList<>();

            StringTokenizer tokenizer = new StringTokenizer(value, "|");
            while (tokenizer.hasMoreElements()) {
                String token = tokenizer.nextToken().trim();
                if (token.length() > 0) {
                    SortParameterElement sort;
                    if (token.startsWith("-")) {
                        sort = new SortParameterElement(token.substring(1).trim(), false);
                    }
                    else if (token.startsWith("+")) {
                        sort = new SortParameterElement(token.substring(1).trim(), true);
                    }
                    else {
                        sort = new SortParameterElement(token, true);
                    }
                    sorts.add(sort);
                }
            }
            return sorts;
        }
    }
}
