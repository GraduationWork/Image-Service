import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Iterators;

public class GuavaTest {

//    @Test
    public void testJoinerClass() {
        List<String> values = Arrays.asList("First", "Second", null, "Third");
        List<String> values2 = Arrays.asList(null,null);
        String useForNull = Joiner.on("/").useForNull("NULLABLE").join(values);
        String skipsForNull = Joiner.on("/").skipNulls().join(values);
        System.out.println(useForNull);
        System.out.println(skipsForNull);
    }

//    @Test
    public void testMapJoiner() {
        Map<String,String> values = new HashMap<String, String>();
        values.put("key1", "value1");
        values.put("key2", "value2");
        System.out.println(Joiner.on("#").withKeyValueSeparator("=").join(values));
    }
    
//    @Test
    public void testSplitter() {
        List<String> values = Splitter.on("|").splitToList("1|2|3|4|5|6");
        System.out.println(Joiner.on("*").skipNulls().join(values));
    }
    
//    @Test
    public void comparisonTest() {
        //can be used in compare method
        ComparisonChain.start()
            .compareFalseFirst(true, true)
            .result();
        DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
        DateTime end = new DateTime(2005, 1, 1, 0, 0, 0, 0);
        Interval interval = new Interval(start, end);
        interval.getChronology().minutes();
    }
    
    
    
}
