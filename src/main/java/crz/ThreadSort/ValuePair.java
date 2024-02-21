
package crz.ThreadSort;

class ValuePair implements Comparable<ValuePair>
{

  private int value;
  private int index;

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

  @Override
  public int compareTo(ValuePair vp)
  {
    return Integer.compare(getValue(), vp.getValue());
  }

}
