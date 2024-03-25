
package crz.ThreadSort;

// Class representing a pair of value and its corresponding index
class ValuePair implements Comparable<ValuePair>
{

  private int value;
  private int index;

  // Constructor to initialize the value pair
  public ValuePair(int value, int index)
  {

    this.value = value;
    this.index = index;

  }

  public int getValue()
  {
    return value;
  }

  public int getFromIndex()
  {
    return index;
  }

  // Method to compare two ValuePairs based ONLY on their values
  @Override
  public int compareTo(ValuePair vp)
  {
    return Integer.compare(getValue(), vp.getValue());
  }

}
