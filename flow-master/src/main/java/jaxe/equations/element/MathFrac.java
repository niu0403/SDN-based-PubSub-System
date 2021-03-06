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
 * This math element presents a mathematical fraction
 *
 * @author <a href="mailto:stephan@vern.chem.tu-berlin.de">Stephan Michels</a>
 * @author <a href="mailto:sielaff@vern.chem.tu-berlin.de">Marco Sielaff</a>
 * @version %I%, %G%
 */
public class MathFrac extends MathElement
{
  /** The XML element from this class */
  public final static String ELEMENT = "mfrac";

  /** Attribute name of the linethickness property */
  public final static String ATTRIBUTE_LINETHICKNESS = "linethickness"; 

    private int linethickness = 1;

    /**
     * Sets the thickness of the fraction line
     *
     * @param linethickness Thickness
     */
    public void setLineThickness(final int linethickness)
    {
        if (linethickness >= 0)
            this.linethickness = linethickness;
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

        final int middle = posY - getMiddleShift();

        final int width = getWidth(true);

        e1.paint(g, posX + (width - e1.getWidth(true)) / 2,
                         middle - e1.getDescentHeight(true) - 1);

        for (int i = 0; i < linethickness; i++)
            g.drawLine(posX + 1, middle + i - linethickness / 2,
                                 posX + width - 1, middle + i - linethickness / 2);

        e2.paint(g, posX + (width - e2.getWidth(true)) / 2,
                         middle + e2.getAscentHeight(true) + 1);
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
        return Math.max(getMathElement(0).getWidth(dynamicParts), getMathElement(1).getWidth(dynamicParts)) + 4;
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
        return getAscentHeight(true) + getDescentHeight(true);
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
        return getMathElement(0).getHeight(true) + 1 + linethickness / 2 + getMiddleShift();
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
        return Math.max(0, getMathElement(1).getHeight(true) + 1 + linethickness / 2 - getMiddleShift());
    }
}
