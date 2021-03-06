// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.business.diagram.custom.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * DOC mhelleboid class global comment. Detailled comment <br/>
 * 
 * $Id: ActionBusinessItemShapeFigure.java 1 2006-09-29 17:06:40 +0000 (ven, 29 sep 2006) nrousseau $
 * 
 */
public class ActorBusinessItemShapeFigure extends BusinessItemShapeFigure {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void paintFigure(Graphics graphics) {

        if (getDrawFrame()) {
            setDefaultSize(60, 60);
            setBorder(border);
            drawFigure(getSmallBounds(), graphics);
        } else {
            if (getBorder() != null) {
                setBorder(null);
            }
            drawFigure(getInnerBounds(), graphics);
        }

    }

    private void drawFigure(Rectangle r, Graphics graphics) {

        int headHeight = (int) (r.height * 0.25);

        int legsHeight = (int) (r.height * 0.3);
        int legsSpace = (int) (r.width * 0.15);

        int armsHeigth = (int) (headHeight + r.height * 0.2);
        int armsLength = (int) (legsSpace * 2);
        int armsOffset = (int) (r.height * 0.03);

        // head
        Rectangle head = new Rectangle(r.x + r.width / 2 - headHeight / 2, r.y, headHeight, headHeight);
        graphics.fillOval(head);
        graphics.drawOval(head);

        // body
        graphics.drawLine(r.x + r.width / 2, r.y + headHeight, r.x + r.width / 2, r.y + r.height - legsHeight);

        // legs
        graphics.drawLine(r.x + r.width / 2, r.y + r.height - legsHeight, r.x + r.width / 2 + legsSpace, r.y + r.height);
        graphics.drawLine(r.x + r.width / 2, r.y + r.height - legsHeight, r.x + r.width / 2 - legsSpace, r.y + r.height);

        // arms
        graphics.drawLine(r.x + r.width / 2, r.y + armsHeigth, r.x + r.width / 2 - armsLength, r.y + armsHeigth - armsOffset);
        graphics.drawLine(r.x + r.width / 2, r.y + armsHeigth, r.x + r.width / 2 + armsLength, r.y + armsHeigth - armsOffset);
    }
}
