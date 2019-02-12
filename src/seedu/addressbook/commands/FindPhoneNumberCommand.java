package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Finds and lists all persons in address book whose telephone number is the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindPhoneNumberCommand extends Command {

    public static final String COMMAND_WORD = "findPhoneNumber";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the person whose telephone number is "
            + "the specified keywords (case-sensitive) and displays the person's information.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 12345678";

    private final Set<String> keywords;

    public FindPhoneNumberCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns a copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> numberFound = getPersonsWithNameContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(numberFound), numberFound);
    }

    /**
     * Retrieves all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithNameContainingAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<String> wordsInName = new HashSet<>(person.getPhone().getWordsInPhone());
            if (!Collections.disjoint(wordsInName, keywords)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

}
