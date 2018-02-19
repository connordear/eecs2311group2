/*
 * Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 1996-2002.
 * All rights reserved. Software written by Ian F. Darwin and others.
 * $Id: LICENSE,v 1.8 2004/02/09 03:33:38 ian Exp $
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * Java, the Duke mascot, and all variants of Sun's Java "steaming coffee
 * cup" logo are trademarks of Sun Microsystems. Sun's, and James Gosling's,
 * pioneering role in inventing and promulgating (and standardizing) the Java 
 * language and environment is gratefully acknowledged.
 * 
 * The pioneering role of Dennis Ritchie and Bjarne Stroustrup, of AT&T, for
 * inventing predecessor languages C and C++ is also gratefully acknowledged.
 */

package authoringApp;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * CustomListModel combines an ArrayList with a ListModel for ease of use.
 */
public class CustomListModel extends ArrayList implements ListModel {

	protected Object source;
	private ArrayList listeners = new ArrayList();

	CustomListModel(Object src) {
		source = src;
	}

	@Override
	public Object getElementAt(int index) {
		return get(index);
	}

	@Override
	public int getSize() {
		return size();
	}

	@Override
	public void removeListDataListener(javax.swing.event.ListDataListener l) {
		listeners.remove(l);
	}

	@Override
	public void addListDataListener(javax.swing.event.ListDataListener l) {
		listeners.add(l);
	}

	void notifyListeners() {
		// no attempt at optimziation
		ListDataEvent le = new ListDataEvent(source, ListDataEvent.CONTENTS_CHANGED, 0, getSize());
		for (int i = 0; i < listeners.size(); i++) {
			((ListDataListener) listeners.get(i)).contentsChanged(le);
		}
	}

	// REMAINDER ARE OVERRIDES JUST TO CALL NOTIFYLISTENERS

	public boolean add(Object o) {
		boolean b = super.add(o);
		if (b)
			notifyListeners();
		return b;
	}

	public void add(int index, Object element) {
		super.add(index, element);
		notifyListeners();
	}

	public boolean addAll(Collection o) {
		boolean b = super.add(o);
		if (b)
			notifyListeners();
		return b;
	}

	public void clear() {
		super.clear();
		notifyListeners();
	}

	public Object remove(int i) {
		Object o = super.remove(i);
		notifyListeners();
		return o;
	}

	public boolean remove(Object o) {
		boolean b = super.remove(o);
		if (b)
			notifyListeners();
		return b;
	}

	public Object set(int index, Object element) {
		Object o = super.set(index, element);
		notifyListeners();
		return o;
	}
}
