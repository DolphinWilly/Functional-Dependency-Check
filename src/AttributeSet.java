import java.util.*;

public class AttributeSet extends HashSet<Attribute> {

	public AttributeSet() {
		super();
	}

	public AttributeSet(AttributeSet other) {
		super(other);
	}
	
	// provide set contains function for AttributeSet
	public boolean contains(AttributeSet aSet) {
		for(Attribute a: aSet) {
			if(!this.contains(a))
				return false;		
		}
		return true;
	}
	
	public AttributeSet intersection(AttributeSet aSet) {
		AttributeSet result = new AttributeSet();
		for (Attribute a: aSet) {
			if (this.contains(a))
				result.add(a);
		}
		return result;
	}
	
	public AttributeSet union(AttributeSet aSet) {
		AttributeSet result = new AttributeSet();
		result.addAll(this);
		result.addAll(aSet);
		return result;
	}
	
}