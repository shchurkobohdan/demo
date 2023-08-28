package ui.pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public abstract class Page {

    public Page() {
        page(this);
    }

    public void reloadPage() {
        Selenide.refresh();
    }
}
