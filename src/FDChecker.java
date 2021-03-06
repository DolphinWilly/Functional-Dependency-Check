import java.util.*;

public class FDChecker {

	/**
	 * Checks whether a decomposition of a table is dependency
	 * preserving under the set of functional dependencies fds
	 * 
	 * @param t1 one of the two tables of the decomposition
	 * @param t2 the second table of the decomposition
	 * @param fds a complete set of functional dependencies that apply to the data
	 * 
	 * @return true if the decomposition is dependency preserving, false otherwise
	 **/
	public static boolean checkDepPres(AttributeSet t1, AttributeSet t2, Set<FunctionalDependency> fds) {
		for (FunctionalDependency fd: fds) {
			AttributeSet result = new AttributeSet(fd.left);
			int currentSize = result.size();
			int changedSize = result.size();
			do {
				currentSize = result.size();
				AttributeSet X = new AttributeSet(t1);
				AttributeSet Y = new AttributeSet(t2);
				AttributeSet temp1 = X.intersection(result);
				AttributeSet temp2 = Y.intersection(result);
				temp1 = closure (temp1, fds).intersection(X);
				temp2 = closure (temp2, fds).intersection(Y);
				result = result.union(temp1);
				result = result.union(temp2);
				changedSize = result.size();
			} while(currentSize < changedSize);
		if(!result.contains(fd.right))
			return false;
		}
		return true;
	}

	/**
	 * Checks whether a decomposition of a table is lossless
	 * under the set of functional dependencies fds
	 * 
	 * @param t1 one of the two tables of the decomposition
	 * @param t2 the second table of the decomposition
	 * @param fds a complete set of functional dependencies that apply to the data
	 * 
	 * @return true if the decomposition is lossless, false otherwise
	 **/
	public static boolean checkLossless(AttributeSet t1, AttributeSet t2, Set<FunctionalDependency> fds) {
		//a decomposition is lossless if the common attributes is a superkey for one of the tables
		AttributeSet intersec = t1.intersection(t2);
		AttributeSet cl = closure(intersec, fds);
		if (cl.contains(t1) || cl.contains(t2))
			return true;
		return false;
	}

	//finds the total set of attributes implied by attrs
	// change to public for testing
	public static AttributeSet closure(AttributeSet attrs, Set<FunctionalDependency> fds) {
		AttributeSet closure = new AttributeSet (attrs);		
		int currentSize;
		int changedSize = closure.size();
		do {
			currentSize = closure.size();
			for(FunctionalDependency fd: fds) {				
				if(closure.contains(fd.left)) 
					closure.add(fd.right);				
			}
			changedSize = closure.size();
		}while(currentSize < changedSize);
		return closure;
	}
}
