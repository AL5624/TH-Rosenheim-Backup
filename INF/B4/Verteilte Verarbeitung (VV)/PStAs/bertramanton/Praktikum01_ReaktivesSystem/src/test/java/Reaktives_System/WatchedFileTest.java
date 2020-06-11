package Reaktives_System;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WatchedFileTest
{
    Path             path             = Paths.get("I:tmp2");
    WatchedFile      watchedFile      = new WatchedFile("abc", path);

    @BeforeClass
    public static void setDirectoryWatcher()
    {
        WatchedDirectory watchedDirectory = new WatchedDirectory();
        DirectoryWatcher directoryWatcher = new DirectoryWatcher(watchedDirectory);
    }

    @Test
    public void setStateCreatedToCreatedWithCreate()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);
        assertThat("created --> created: CREATE", watchedFile.getState(), equalTo(WatchedFile.State.CREATED));
    }

    @Test
    public void setStateCreatedToCreatedWithModify()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.MODIFY);
        assertThat("created --> created: MODIFY", watchedFile.getState(), equalTo(WatchedFile.State.CREATED));
    }

    @Test
    public void setStateCreatedToGone()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.DELETED);
        assertThat("created --> gone: DELETED", watchedFile.getState(), equalTo(WatchedFile.State.GONE));
    }

    @Test
    public void setStateCreatedToInsync()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        assertThat("created --> insync: SYNC", watchedFile.getState(), equalTo(WatchedFile.State.INSYNC));
    }

    @Test
    public void setStateInsyncToInsync()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);

        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        assertThat("insync --> insync: SYNC", watchedFile.getState(), equalTo(WatchedFile.State.INSYNC));
    }

    @Test
    public void setStateInsyncToModifiedWithCreate()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);

        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);
        assertThat("insync --> modified: CREATE", watchedFile.getState(), equalTo(WatchedFile.State.MODIFIED));
    }

    @Test
    public void setStateInsyncToModifiedWithModify()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);

        watchedFile.setState(DirectoryWatcher.Symbol.MODIFY);
        assertThat("insync --> modified: MODIFY", watchedFile.getState(), equalTo(WatchedFile.State.MODIFIED));
    }

    @Test
    public void setStateInsyncToDeleted()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);

        watchedFile.setState(DirectoryWatcher.Symbol.DELETED);
        assertThat("insync --> deleted: DELETED", watchedFile.getState(), equalTo(WatchedFile.State.DELETED));
    }

    @Test
    public void setStateModifiedToModifiedWithCreate()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);

        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);
        assertThat("modified --> modified: CREATE", watchedFile.getState(), equalTo(WatchedFile.State.MODIFIED));
    }

    @Test
    public void setStateModifiedToModifiedWithModify()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);

        watchedFile.setState(DirectoryWatcher.Symbol.MODIFY);
        assertThat("modified --> modified: MODIFY", watchedFile.getState(), equalTo(WatchedFile.State.MODIFIED));
    }

    @Test
    public void setStateModifiedToInsync()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);

        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        assertThat("modified --> insync: SYNC", watchedFile.getState(), equalTo(WatchedFile.State.INSYNC));
    }

    @Test
    public void setStateModifiedToDeleted()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);

        watchedFile.setState(DirectoryWatcher.Symbol.DELETED);
        assertThat("modified --> deleted: DELETED", watchedFile.getState(), equalTo(WatchedFile.State.DELETED));
    }

    @Test
    public void setStateDeletedToDelete()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);
        watchedFile.setState(DirectoryWatcher.Symbol.DELETED);

        watchedFile.setState(DirectoryWatcher.Symbol.DELETED);
        assertThat("deleted --> deleted: DELETED", watchedFile.getState(), equalTo(WatchedFile.State.DELETED));
    }

    @Test
    public void setStateDeletedToModifiedWithCreate()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);
        watchedFile.setState(DirectoryWatcher.Symbol.DELETED);

        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);
        assertThat("deleted --> modified: CREATE", watchedFile.getState(), equalTo(WatchedFile.State.MODIFIED));
    }

    @Test
    public void setStateDeletedToModifiedWithModify()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);
        watchedFile.setState(DirectoryWatcher.Symbol.DELETED);

        watchedFile.setState(DirectoryWatcher.Symbol.MODIFY);
        assertThat("deleted --> modified: MODIFY", watchedFile.getState(), equalTo(WatchedFile.State.MODIFIED));
    }

    @Test
    public void setStateDeletedToGone()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);
        watchedFile.setState(DirectoryWatcher.Symbol.DELETED);

        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        assertThat("deleted --> gone: SYNC", watchedFile.getState(), equalTo(WatchedFile.State.GONE));
    }

    @Test
    public void setStateGoneToGoneWithSync()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);
        watchedFile.setState(DirectoryWatcher.Symbol.DELETED);
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);

        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        assertThat("gone --> gone: SYNC", watchedFile.getState(), equalTo(WatchedFile.State.GONE));
    }

    @Test
    public void setStateGoneToGoneWithModify()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);
        watchedFile.setState(DirectoryWatcher.Symbol.DELETED);
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);

        watchedFile.setState(DirectoryWatcher.Symbol.MODIFY);
        assertThat("gone --> gone: MODIFY", watchedFile.getState(), equalTo(WatchedFile.State.GONE));
    }

    @Test
    public void setStateGoneToGoneWithDeleted()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);
        watchedFile.setState(DirectoryWatcher.Symbol.DELETED);
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);

        watchedFile.setState(DirectoryWatcher.Symbol.DELETED);
        assertThat("gone --> gone: DELETED", watchedFile.getState(), equalTo(WatchedFile.State.GONE));
    }

    @Test
    public void setStateGoneToCreated()
    {
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);
        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);
        watchedFile.setState(DirectoryWatcher.Symbol.DELETED);
        watchedFile.setState(DirectoryWatcher.Symbol.SYNC);

        watchedFile.setState(DirectoryWatcher.Symbol.CREATE);
        assertThat("gone --> created: CREATE", watchedFile.getState(), equalTo(WatchedFile.State.CREATED));
    }
}