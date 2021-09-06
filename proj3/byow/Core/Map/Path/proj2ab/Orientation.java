package byow.Core.Map.Path.proj2ab;

public enum Orientation {
	VERTICAL,
	HORIZONTAL;

	public Orientation opposite() {
		if (this == Orientation.HORIZONTAL) {
			return VERTICAL;
		} else {
			return HORIZONTAL;
		}
	}
}
