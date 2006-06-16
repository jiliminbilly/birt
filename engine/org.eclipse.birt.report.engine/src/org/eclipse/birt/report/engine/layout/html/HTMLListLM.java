/***********************************************************************
 * Copyright (c) 2004 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Actuate Corporation - initial API and implementation
 ***********************************************************************/

package org.eclipse.birt.report.engine.layout.html;

import org.eclipse.birt.report.engine.content.IBandContent;
import org.eclipse.birt.report.engine.content.IListContent;

public class HTMLListLM extends HTMLBlockStackingLM
{

	public HTMLListLM( HTMLLayoutManagerFactory factory )
	{
		super( factory );
	}

	public int getType( )
	{
		return LAYOUT_MANAGER_LIST;
	}

	protected void repeatHeader( )
	{
		IListContent list = (IListContent) content;
		if ( list.isHeaderRepeat( ) )
		{
			IBandContent header = list.getHeader( );
			if ( header != null )
			{
				boolean pageBreak = context.allowPageBreak( );
				boolean skipPageHint = context.getSkipPageHint( );
				context.setAllowPageBreak( pageBreak );
				context.setSkipPageHint( true );
				engine.layout( header, emitter );
				context.setAllowPageBreak( pageBreak );
				context.setSkipPageHint( skipPageHint );
			}
		}
	}

	protected boolean layoutChildren( )
	{
		repeatHeader( );
		boolean hasNext = super.layoutChildren( );
		return hasNext;
	}

}
