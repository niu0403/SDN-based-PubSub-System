/*
Jaxe - Editeur XML en Java

Copyright (C) 2003 Observatoire de Paris-Meudon

Ce programme est un logiciel libre ; vous pouvez le redistribuer et/ou le modifier conform�ment aux dispositions de la Licence Publique G�n�rale GNU, telle que publi�e par la Free Software Foundation ; version 2 de la licence, ou encore (� votre choix) toute version ult�rieure.

Ce programme est distribu� dans l'espoir qu'il sera utile, mais SANS AUCUNE GARANTIE ; sans m�me la garantie implicite de COMMERCIALISATION ou D'ADAPTATION A UN OBJET PARTICULIER. Pour plus de d�tail, voir la Licence Publique G�n�rale GNU .

Vous devez avoir re�u un exemplaire de la Licence Publique G�n�rale GNU en m�me temps que ce programme ; si ce n'est pas le cas, �crivez � la Free Software Foundation Inc., 675 Mass Ave, Cambridge, MA 02139, Etats-Unis.
*/

package jaxe.equations.element;

import java.awt.Graphics;

/**
 * This class arrange a element over an other element
 *
 * @author <a href="mailto:stephan@vern.chem.tu-berlin.de">Stephan Michels</a>
 * @author <a href="mailto:sielaff@vern.chem.tu-berlin.de">Marco Sielaff</a>
 * @version %I%, %G%
 */
public class MathOver extends MathElement
{

  /** The XML element from this class */
  public final static String ELEMENT = "mover";
  
  private boolean accent = false;

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
            if (getMathElementCount() == 2 && !accent)
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
    if (getMathElement(1) != null && !accent)
        getMathElement(1).setFontSize(getFontSize()-2);
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

        final int width = getWidth(true);

        e1.paint(g, posX + (width - e1.getWidth(true)) / 2, posY);
        if (accent) {
            int h;
            if (e1 instanceof MathText || e1 instanceof MathIdentifier)
                h = e1.getRealAscentHeight(g) + 3;
            else
                h = e1.getAscentHeight(true);
            e2.paint(g, posX + (width - e2.getWidth(true)) / 2,
                         posY - h);
        } else
            e2.paint(g, posX + (width - e2.getWidth(true)) / 2,
                         posY - (e1.getAscentHeight(true) + e2.getDescentHeight(true)));
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
                                        getMathElement(1).getWidth(dynamicParts));
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
        if (accent)
            return getMathElement(0).getHeight(dynamicParts) + getMathElement(1).getAscentHeight(dynamicParts) + 1;
        else
            return getMathElement(0).getHeight(dynamicParts) + getMathElement(1).getHeight(dynamicParts);
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
        if (accent)
            return getMathElement(0).getAscentHeight(true) + getMathElement(1).getAscentHeight(true);
        else
            return getMathElement(0).getAscentHeight(true) + getMathElement(1).getHeight(true);
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
        return getMathElement(0).getDescentHeight(true);
    }
    
    public void setAccent(final boolean accent) {
        this.accent = accent;
    }
    
    public boolean getAccent() {
        return accent;
    }
}
