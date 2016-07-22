package com.psddev.dari.db.h2;

import com.psddev.dari.db.Location;
import com.psddev.dari.db.Region;
import org.junit.Test;

public class RegionIndexTest extends AbstractIndexTest<Region> {

    @Override
    protected Class<? extends Model<Region>> modelClass() {
        return Foo.class;
    }

    @Override
    protected Region value(int index) {
        return Region.sphericalCircle(0.0d, 0.0d, index + 1);
    }

    @Override
    @Test
    public void contains() {
        createCompareTestModels();
        assertCount(total, "field contains ?", new Location(0.0d, 0.0d));
        assertCount(total - 1, "field contains ?", new Location(1.5d, 0.0d));
        assertCount(total, "field contains ?", Region.sphericalCircle(0.0d, 0.0d, 0.5d));
        assertCount(total - 1, "field contains ?", Region.sphericalCircle(0.0d, 0.0d, 1.5d));
    }

    @Test(expected = IllegalArgumentException.class)
    public void containsIllegal() {
        createCompareTestModels();
        query().where("field contains true").count();
    }

    @Test(expected = IllegalArgumentException.class)
    public void gtIllegal() {
        createCompareTestModels();
        query().where("field > true").count();
    }

    @Test(expected = IllegalArgumentException.class)
    public void geIllegal() {
        createCompareTestModels();
        query().where("field > true").count();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ltIllegal() {
        createCompareTestModels();
        query().where("field < true").count();
    }

    @Test(expected = IllegalArgumentException.class)
    public void leIllegal() {
        createCompareTestModels();
        query().where("field <= true").count();
    }

    public static class Foo extends Model<Region> {
    }
}
