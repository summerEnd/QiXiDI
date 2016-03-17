package com.sumauto.widget.nav.title;

import android.view.View;

public interface ITab {
    View getView();

    void setTabSelect(boolean selected);

    boolean isTabSelected();
}
