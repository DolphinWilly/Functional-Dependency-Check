import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;
public class ProgressTest {

	// test closure
	@Test
	public void closureTest() {
		Attribute a = new Attribute ("A");
		Attribute b = new Attribute ("B");
		Attribute c = new Attribute ("C");
		Attribute d = new Attribute ("D");
		AttributeSet aSet = new AttributeSet();
		aSet.add(a);
		AttributeSet l1 = new AttributeSet();
		AttributeSet l2 = new AttributeSet();
		AttributeSet l3 = new AttributeSet();
		l1.add(a);
		l2.add(b);
		l3.add(c);
		l3.add(b);
		FunctionalDependency fd1 = new FunctionalDependency (l1,b);
		FunctionalDependency fd2 = new FunctionalDependency (l2,c);
		FunctionalDependency fd3 = new FunctionalDependency (l3,d);
		Set<FunctionalDependency> fds1 = new HashSet<FunctionalDependency>();
		fds1.add(fd1);
		fds1.add(fd2);
		fds1.add(fd3);
		AttributeSet cl = FDChecker.closure(aSet,fds1);
		assertEquals(cl.size(), 4);
		AttributeSet test1 = new AttributeSet();
		test1.add(a);
		test1.add(b);
		test1.add(c);
		test1.add(d);
		assertEquals(cl,test1);
		
		Set<FunctionalDependency> fds2 = new HashSet<FunctionalDependency>();
		AttributeSet l4 = new AttributeSet();
		l4.add(c);
		FunctionalDependency fd4 = new FunctionalDependency (l1,c);
		FunctionalDependency fd5 = new FunctionalDependency (l2,d);
		FunctionalDependency fd6 = new FunctionalDependency (l4,d);
		fds2.add(fd4);
		fds2.add(fd5);
		fds2.add(fd6);
		AttributeSet cl2 = FDChecker.closure(aSet,fds2);
		assertEquals(cl2.size(), 3);

	}

}
