
package jaxe.equations.element;

import java.awt.Graphics;

/**
 * This class arrange an element under, and an other element over
 * an element
 *
 * @author <a href="mailto:stephan@vern.chem.tu-berlin.de">Stephan Michels</a>
 * @author <a href="mailto:sielaff@vern.chem.tu-berlin.de">Marco Sielaff</a>
 * @version %I%, %G%
 */
public class MathUnderOver extends MathElement
{

  /** The XML element from this class */
  public final static String ELEMENT = "munderover";

    /**
     * Add a math element as a child
     *
     * @param child Math element
     */
    @Override
    public void addMathElement(final MathElement child)
    {
        super.addMathElement(child);
        if (child != null)
        {
            if ((getMathElementCount() == 2) || (getMathElementCount() == 3))
                child.setFontSize(getFontSize() - 2);
            else
                child.setFontSize(getFontSize());
        }
    }

  /**
   * Sets the font size for this component
   *
   * @param fontsize Font size
   */
  @Override
public void setFontSize(final int fontsize)
  {
    super.setFontSize(fontsize);
    if (getMathElement(1)!=null)
      getMathElement(1).setFontSize(getFontSize()-2);
    if (getMathElement(2)!=null)
      getMathElement(2).setFontSize(getFontSize()-2);
  }

    /**
     * Paints this element
     *
     * @param g The graphics context to use for painting
     * @param posX The first left position for painting
     * @param posY The position of the baseline
     */
    @Override
    public void paint(final Graphics g, final int posX, final int posY)
    {
        final MathElement e1 = getMathElement(0);
        final MathElement e2 = getMathElement(1);
        final MathElement e3 = getMathElement(2);

        final int width = getWidth(true);

        e1.paint(g, posX + (width - e1.getWidth(true)) / 2, posY);
        e2.paint(g, posX + (width - e2.getWidth(true)) / 2,
                         posY + e1.getDescentHeight(true) + e2.getAscentHeight(true));
        e3.paint(g, posX + (width - e3.getWidth(true)) / 2,
                         posY - (e1.getAscentHeight(true) + e3.getDescentHeight(true)));
    }

    /**
     * Return the current width of this element
     *
     * @param dynamicParts Should be true, if the calculation consider the elements,
     *                     which has not fixed sizes
     *
     * @return Width of this element
     */
    @Override
    public int getWidth(final boolean dynamicParts)
    {
        return Math.max(getMathElement(0).getWidth(dynamicParts),
                                        Math.max(getMathElement(1).getWidth(dynamicParts),
                                                         getMathElement(2).getWidth(dynamicParts)));
    }

    /**
     * Return the current height of this element
     *
     * @param dynamicParts Should be true, if the calculation consider the elements,
     *                     which has not fixed sizes
     *
     * @return Height of this element
     */
    @Override
    public int getHeight(final boolean dynamicParts)
    {
        return getMathElement(0).getHeight(dynamicParts)
                     + getMathElement(1).getHeight(dynamicParts)
                     + getMathElement(2).getHeight(dynamicParts);
    }

    /**
     * Return the current height of the upper part
     * of this component from the baseline
     *
     * @param dynamicParts Should be true, if the calculation consider the elements,
     *                     which has not fixed sizes
     *
     * @return Height of the upper part
     */
    @Override
    public int getAscentHeight(final boolean dynamicParts)
    {
        return getMathElement(0).getAscentHeight(true)
                     + getMathElement(1).getHeight(true);
    }

    /**
     * Return the current height of the lower part
     * of this component from the baseline
     *
     * @param dynamicParts Should be true, if the calculation consider the elements,
     *                     which has not fixed sizes
     *
     * @return Height of the lower part
     */
    @Override
    public int getDescentHeight(final boolean dynamicParts)
    {
        return getMathElement(0).getDescentHeight(true)
                     + getMathElement(2).getHeight(true);
    }
}
