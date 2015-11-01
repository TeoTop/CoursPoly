package tests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ERRTests.class, ACEmptyFileTest.class, ACFileTest.class })
public final class SuiteChaineTestCategoriePartition {}
