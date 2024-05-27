package me.jacob.mcwizards.ui;

import me.jacob.mcwizards.RegisterSpells;
import me.jacob.mcwizards.spellengine.Spell;
import me.jacob.mcwizards.wandengine.ClickEngine;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpellBookUI {
    public static Component spellManual(List<Boolean> combination) {
        Component text = Component.text("");

        Spell spell = RegisterSpells.getSpell(combination);

        text = text.append(Component.text(spell.name).decorate(TextDecoration.BOLD).append(Component.newline()));

        text = text.append(Component.text("Combination: "));
        for (Boolean bool : combination.subList(0, combination.size() - 1)) {
            text = text.append(Component.text(bool ? "R" : "L"));
            text = text.append(Component.text(" + "));
        }
        text = text.append(Component.text(combination.get(combination.size() - 1) ? "R" : "L")).append(Component.newline());

        text = text.append(Component.text("Mana cost: " + spell.getManaCost()).append(Component.newline()));
        text = text.append(Component.newline());
        text = text.append(Component.text(spell.description).append(Component.newline()));
        text = text.append(Component.newline());
        text = text.append(Component.text("[Back]").decorate(TextDecoration.UNDERLINED).color(NamedTextColor.BLUE).clickEvent(ClickEvent.changePage(2)));

        return text;
    }

    public static Component spellManualWithoutBack(List<Boolean> combination) {
        Component text = Component.text("");

        Spell spell = RegisterSpells.getSpell(combination);

        text = text.append(Component.text(spell.name).decorate(TextDecoration.BOLD).append(Component.newline()));

        text = text.append(Component.text("Combination: "));
        for (Boolean bool : combination.subList(0, combination.size() - 1)) {
            text = text.append(Component.text(bool ? "R" : "L"));
            text = text.append(Component.text(" + "));
        }
        text = text.append(Component.text(combination.get(combination.size() - 1) ? "R" : "L")).append(Component.newline());

        text = text.append(Component.text("Mana cost: " + spell.getManaCost()).append(Component.newline()));
        text = text.append(Component.newline());
        text = text.append(Component.text(spell.description));

        return text;
    }

    public static void showSpellManual(Player player) {
        if (ClickEngine.getCombination(player).isEmpty()) {
            player.sendMessage(Component.text("You can't drop wands.").color(NamedTextColor.RED));
        } else {
            Component bookTitle = Component.text("Spell book");
            Component bookAuthor = Component.text("Jacob the great");
            Collection<Component> bookPages = new ArrayList<>();

            bookPages.add(spellManualWithoutBack(ClickEngine.getCombination(player)));

            Book myBook = Book.book(bookTitle, bookAuthor, bookPages);
            player.openBook(myBook);
        }
    }

    public static void openBook(@NonNull Audience target) {
        Component bookTitle = Component.text("Spell book");
        Component bookAuthor = Component.text("Jacob the great");
        Collection<Component> bookPages = new ArrayList<>();

        Component page1 = Component.text("")
                .append(Component.text("        Spell book")).append(Component.newline())
                .append(Component.empty()).append(Component.newline())
                .append(Component.text("In this book you can see all the spells.")).append(Component.newline())
                .append(Component.text("You can also see what they do and how to unlock them.")).append(Component.newline())
                .append(Component.text("All spells are based around an element.")).append(Component.newline())
                .append(Component.text("There are 60 different spells.")).append(Component.newline())
                .append(Component.empty()).append(Component.newline())
                .append(Component.text("[How to cast spells]").decorate(TextDecoration.UNDERLINED).color(NamedTextColor.BLUE).clickEvent(ClickEvent.changePage(3))).append(Component.newline())
                .append(Component.text("[How to bind spells]").decorate(TextDecoration.UNDERLINED).color(NamedTextColor.BLUE).clickEvent(ClickEvent.changePage(4))).append(Component.newline())
                .append(Component.text("[View all spells]").decorate(TextDecoration.UNDERLINED).color(NamedTextColor.BLUE).clickEvent(ClickEvent.changePage(2)));
        bookPages.add(page1);


        Component page2 = Component.text("")
                .append(Component.text("        Spell book")).append(Component.newline())
                .append(Component.empty()).append(Component.newline())
                .append(Component.text("Starter spells").decorate(TextDecoration.BOLD)).append(Component.newline())
                .append(Component.newline())
                .append(Component.text("[Open spell book]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(12)).append(Component.newline()))
                .append(Component.text("[Mage aura]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(13))).append(Component.newline())
                .append(Component.newline())
                .append(Component.text("Elemental spells").decorate(TextDecoration.BOLD)).append(Component.newline())
                .append(Component.empty()).append(Component.newline())
                .append(Component.text("[Air]").color(NamedTextColor.DARK_PURPLE).clickEvent(ClickEvent.changePage(4))).append(Component.newline())
                .append(Component.text("[Earth]").color(NamedTextColor.DARK_PURPLE).clickEvent(ClickEvent.changePage(6))).append(Component.newline())
                .append(Component.text("[Fire]").color(NamedTextColor.DARK_PURPLE).clickEvent(ClickEvent.changePage(8))).append(Component.newline())
                .append(Component.text("[Water]").color(NamedTextColor.DARK_PURPLE).clickEvent(ClickEvent.changePage(10))).append(Component.newline());
        bookPages.add(page2);


        Component page3 = Component.text("")
                .append(Component.text("        Spell book")).append(Component.newline())
                .append(Component.empty()).append(Component.newline())
                .append(Component.text("To cast spells, you will need to click in different combinations with you wand to cast different spells.")).append(Component.newline())
                .append(Component.text("All combinations consist of L and R.")).append(Component.newline())
                .append(Component.newline())
                .append(Component.text("L = [").append(Component.keybind("key.attack")).append(Component.text("]"))).append(Component.newline())
                .append(Component.text("R = [").append(Component.keybind("key.use")).append(Component.text("]")))
                .append(Component.empty()).append(Component.newline())
                .append(Component.text("[Back]").decorate(TextDecoration.UNDERLINED).color(NamedTextColor.BLUE).clickEvent(ClickEvent.changePage(1)));
        bookPages.add(page3);

        Component page4 = Component.text("")
                .append(Component.text("        Spell book")).append(Component.newline())
                .append(Component.empty()).append(Component.newline())
                .append(Component.text("The combinations L and R are special.")).append(Component.newline())
                .append(Component.text("You can bind them to whatever spell you want.")).append(Component.newline())
                .append(Component.text("Use /bindl and /bindr to bind spells to L/R, or click bind to L/R in your spell book")).append(Component.newline())
                .append(Component.text("[Back]").decorate(TextDecoration.UNDERLINED).color(NamedTextColor.BLUE).clickEvent(ClickEvent.changePage(1)));
        bookPages.add(page4);

        Component page5 = Component.text("")
                .append(Component.text("        Spell book")).append(Component.newline())
                .append(Component.empty()).append(Component.newline())
                .append(Component.text("[Back]").decorate(TextDecoration.UNDERLINED).color(NamedTextColor.BLUE).clickEvent(ClickEvent.changePage(1)));
        bookPages.add(page5);


        // Air


        Component page6 = Component.text("")
                .append(Component.text("Air spells").decorate(TextDecoration.BOLD)).append(Component.newline())
                .append(Component.empty()).append(Component.newline())
                .append(Component.text("[Dash]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(10))).append(Component.newline())
                .append(Component.text("[Air Heal]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(11))).append(Component.newline())
                .append(Component.text("[Levitation]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(12))).append(Component.newline())
                .append(Component.text("[Thunder Bolt]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(13))).append(Component.newline())
                .append(Component.text("[none]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(14))).append(Component.newline())
                .append(Component.text("[none]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(15))).append(Component.newline())
                .append(Component.text("[none]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(16))).append(Component.newline())
                .append(Component.text(" [Back]").color(NamedTextColor.BLUE).clickEvent(ClickEvent.changePage(2)));
        bookPages.add(page6);


        // Earth


        Component page7 = Component.text("")
                .append(Component.text("Earth spells").decorate(TextDecoration.BOLD)).append(Component.newline())
                .append(Component.empty()).append(Component.newline())
                .append(Component.text("[Boulder]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(17))).append(Component.newline())
                .append(Component.text("[Earth Fangs]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(18))).append(Component.newline())
                .append(Component.text("[Earth Fangs II]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(19))).append(Component.newline())
                .append(Component.text("[none]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(20))).append(Component.newline())
                .append(Component.text("[none]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(21))).append(Component.newline())
                .append(Component.text("[none]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(22))).append(Component.newline())
                .append(Component.text("[none]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(23))).append(Component.newline())
                .append(Component.text(" [Back]").color(NamedTextColor.BLUE).clickEvent(ClickEvent.changePage(2)));
        bookPages.add(page7);




        // Fire


        Component page8 = Component.text("")
                .append(Component.text("Fire spells").decorate(TextDecoration.BOLD)).append(Component.newline())
                .append(Component.empty()).append(Component.newline())
                .append(Component.text("[Fireball]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(24))).append(Component.newline())
                .append(Component.text("[Fire Circle]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(25))).append(Component.newline())
                .append(Component.text("[Fire Rain]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(26))).append(Component.newline())
                .append(Component.text("[none]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(27))).append(Component.newline())
                .append(Component.text("[none]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(28))).append(Component.newline())
                .append(Component.text("[none]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(29))).append(Component.newline())
                .append(Component.text("[none]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(30))).append(Component.newline())
                .append(Component.text(" [Back]").color(NamedTextColor.BLUE).clickEvent(ClickEvent.changePage(2)));
        bookPages.add(page8);




        // Water


        Component page9 = Component.text("")
                .append(Component.text("Water spells").decorate(TextDecoration.BOLD)).append(Component.newline())
                .append(Component.empty()).append(Component.newline())
                .append(Component.text("[Dolphin's Grace]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(31))).append(Component.newline())
                .append(Component.text("[Water heal]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(32))).append(Component.newline())
                .append(Component.text("[Riptide]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(33))).append(Component.newline())
                .append(Component.text("[none]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(34))).append(Component.newline())
                .append(Component.text("[none]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(35))).append(Component.newline())
                .append(Component.text("[none]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(36))).append(Component.newline())
                .append(Component.text("[none]").color(NamedTextColor.DARK_GREEN).clickEvent(ClickEvent.changePage(37))).append(Component.newline())
                .append(Component.text(" [Back]").color(NamedTextColor.BLUE).clickEvent(ClickEvent.changePage(2)));
        bookPages.add(page9);


        // Air
        // P10
        bookPages.add(spellManual(List.of(true, true)));

        bookPages.add(spellManual(List.of(true, true, false)));
        bookPages.add(spellManual(List.of(true, true, true)));

        bookPages.add(spellManual(List.of(true, true, false, false)));
        bookPages.add(spellManual(List.of(true, true, false, true)));
        bookPages.add(spellManual(List.of(true, true, true, false)));
        bookPages.add(spellManual(List.of(true, true, true, true)));



        // Earth
        // 17
        bookPages.add(spellManual(List.of(true, false)));

        bookPages.add(spellManual(List.of(true, false, false)));
        bookPages.add(spellManual(List.of(true, false, true)));

        bookPages.add(spellManual(List.of(true, false, false, false)));
        bookPages.add(spellManual(List.of(true, false, false, true)));
        bookPages.add(spellManual(List.of(true, false, true, false)));
        bookPages.add(spellManual(List.of(true, false, true, true)));




        // Fire
        // 24
        bookPages.add(spellManual(List.of(false, true)));

        bookPages.add(spellManual(List.of(false, true, false)));
        bookPages.add(spellManual(List.of(false, true, true)));

        bookPages.add(spellManual(List.of(false, true, false, false)));
        bookPages.add(spellManual(List.of(false, true, false, true)));
        bookPages.add(spellManual(List.of(false, true, true, false)));
        bookPages.add(spellManual(List.of(false, true, true, true)));



        // Water

        // 31
        bookPages.add(spellManual(List.of(false, false)));

        bookPages.add(spellManual(List.of(false, false, false)));
        bookPages.add(spellManual(List.of(false, false, true)));

        bookPages.add(spellManual(List.of(false, false, false, false)));
        bookPages.add(spellManual(List.of(false, false, false, true)));
        bookPages.add(spellManual(List.of(false, false, true, false)));
        bookPages.add(spellManual(List.of(false, false, true, true)));
        
        

        Book myBook = Book.book(bookTitle, bookAuthor, bookPages);
        target.openBook(myBook);
    }
}
