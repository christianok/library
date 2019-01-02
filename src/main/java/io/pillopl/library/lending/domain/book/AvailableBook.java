package io.pillopl.library.lending.domain.book;

import io.pillopl.library.lending.domain.library.LibraryBranchId;
import io.pillopl.library.lending.domain.patron.PatronBooksEvent.BookPlacedOnHoldByPatron;
import io.pillopl.library.lending.domain.patron.PatronId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class AvailableBook {

    @Getter
    private final BookInformation bookInformation;

    @Getter
    private final LibraryBranchId libraryBranch;


    public boolean isRestricted () {
        return bookInformation.getBookType().equals(BookType.Restricted);
    }

    public BookId getBookId() {
        return bookInformation.getBookId();
    }

    //TODO add till
    public BookOnHold handle(BookPlacedOnHoldByPatron bookPlacedOnHold) {
        return new BookOnHold(
                new BookInformation(
                        new BookId(bookPlacedOnHold.getBookId()),
                        bookPlacedOnHold.getBookType()),
                new LibraryBranchId(bookPlacedOnHold.getLibraryBranchId()),
                new PatronId(bookPlacedOnHold.getPatronId()),
                null);
    }
}
