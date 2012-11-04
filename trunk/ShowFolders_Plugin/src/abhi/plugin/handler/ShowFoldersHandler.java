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
package abhi.plugin.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.IPackagesViewPart;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import abhi.plugin.activator.Activator;
import abhi.plugin.dialogs.ShowFoldersDialog;

public class ShowFoldersHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		//call the dialog here
		Object resource = null;
		try {
			resource = queryResource();
		} catch (JavaModelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(resource == null) return null;
		
		final IWorkbenchWindow workBenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if(workBenchWindow == null)
			throw new ExecutionException("no active workbench window");
		
		final IWorkbenchPage  workbenchPage = workBenchWindow.getActivePage();
		IViewPart view = workbenchPage.findView(JavaUI.ID_PACKAGES);
		
		// If the package Explorer is not open then open it
		if(view == null)
			try {
				view  = workbenchPage.showView(JavaUI.ID_PACKAGES);
			} catch (PartInitException e) {
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR,Activator.PLUGIN_ID,e.getMessage()));
				//e.printStackTrace();
			}
		IPackagesViewPart part = (IPackagesViewPart)view;
		part.selectAndReveal(resource);
		return null;
	}

	private Object queryResource() throws JavaModelException {
		
/*		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("WithPrefix");
		IJavaProject javaProject =JavaCore.create(project);
		IPackageFragmentRoot initRoot= null;
		IPackageFragmentRoot[] roots= javaProject.getPackageFragmentRoots();
		for (int i= 0; i < roots.length; i++) {
			if (roots[i].getKind() == IPackageFragmentRoot.K_SOURCE) {
				initRoot= roots[i];
				break;
			}
		}
		
		IPackageFragment pack = initRoot.getPackageFragment("com.informatica.cloud.adapter.withprefix.metadata");
		return pack;*/
		final IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		if (window == null) {
			return null;
		}
		final Shell parent = window.getShell();
		final IContainer input = ResourcesPlugin.getWorkspace().getRoot();

		final ShowFoldersDialog dialog = new ShowFoldersDialog(parent,false,input,IResource.FOLDER|IResource.PROJECT); 
		final int resultCode = dialog.open();
		if (resultCode != Window.OK) {
			return null;
		}

		final Object[] result = dialog.getResult();

		return  result[0];
		
	}

}
