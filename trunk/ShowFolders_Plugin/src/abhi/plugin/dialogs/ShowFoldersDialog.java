/*      Copyright (C) 2012  Abhinandan M Kulkarni

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see http://www.gnu.org/licenses/.
*/
package abhi.plugin.dialogs;

import org.eclipse.core.resources.IContainer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

import abhi.plugin.activator.ShowFoldersWorkBenchMessages;

/**
 * @author Abhinandan M K
 *
 */
public class ShowFoldersDialog extends FilteredResourcesSelectionDialog {

	public ShowFoldersDialog(Shell shell, boolean multi, IContainer container,
			int typesMask) {
		super(shell, multi, container, typesMask);
		setTitle(ShowFoldersWorkBenchMessages.SHOW_FOLDERS);
	}
	
	
	//override the follwing method to include packages.. 
	/*protected ItemsFilter createFilter() {
		return new ResourceFilter(container, searchContainer, isDerived, typeMask);
	}*/
}
