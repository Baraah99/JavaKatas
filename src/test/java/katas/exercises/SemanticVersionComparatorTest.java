package katas.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SemanticVersionComparatorTest {

    @Test
    public void testCompareVersions_LesserVersion() {
        assertEquals(-1, SemanticVersionComparator.compareVersions("1.0.0", "1.0.1"));
        assertEquals(-1, SemanticVersionComparator.compareVersions("1.0.0", "1.1.0"));
        assertEquals(-1, SemanticVersionComparator.compareVersions("1.0.0", "2.0.0"));
        assertEquals(-1, SemanticVersionComparator.compareVersions("1.1.0", "1.2.0"));
    }

    @Test
    public void testCompareVersions_GreaterVersion() {
        assertEquals(1, SemanticVersionComparator.compareVersions("1.0.1", "1.0.0"));
        assertEquals(1, SemanticVersionComparator.compareVersions("1.1.0", "1.0.0"));
        assertEquals(1, SemanticVersionComparator.compareVersions("2.0.0", "1.0.0"));
        assertEquals(1, SemanticVersionComparator.compareVersions("1.2.0", "1.1.0"));
    }

    @Test
    public void testCompareVersions_EqualVersions() {
        assertEquals(0, SemanticVersionComparator.compareVersions("1.0.0", "1.0.0"));
        assertEquals(0, SemanticVersionComparator.compareVersions("2.1.0", "2.1.0"));
        assertEquals(0, SemanticVersionComparator.compareVersions("3.4.5", "3.4.5"));
    }

    @Test
    public void testCompareVersions_VersionsWithLeadingZeros() {
        assertEquals(0, SemanticVersionComparator.compareVersions("01.02.03", "1.2.3"));
        assertEquals(1, SemanticVersionComparator.compareVersions("01.02.03", "1.1.0"));
        assertEquals(-1, SemanticVersionComparator.compareVersions("1.1.0", "01.02.03"));
    }
}
