package oo.hide;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PointSetTests {

    // NB! If tests in EqualityTests do not pass then
    // there's no point running tests in this class.


    @Test
    public void hasStringRepresentation2() {
        PointSet pointSet = new PointSet();

        pointSet.add(new Point(1, 2));
        pointSet.add(new Point(1, 2));


    }

    @Test
    public void hasStringRepresentation() {
        PointSet set = new PointSet();

        set.add(new Point(1, 1));
        set.add(new Point(2, 1));
        set.add(new Point(1, 2));

        assertThat(set.toString()).isEqualTo("(1, 1), (2, 1), (1, 2)");
    }

    @Test
    public void keepsTrackOfTheSize() {
        PointSet set = new PointSet();

        assertThat(set.size()).isEqualTo(0);

        set.add(new Point(1, 1));

        assertThat(set.size()).isEqualTo(1);

        set.add(new Point(2, 1));

        assertThat(set.size()).isEqualTo(2);
    }

    @Test
    public void tellsWhetherSetContainsAPoint() {
        PointSet set = new PointSet();

        set.add(new Point(1, 1));
        set.add(new Point(1, 2));

        assertTrue(set.contains(new Point(1, 1)));
        assertTrue(set.contains(new Point(1, 2)));
        assertFalse(set.contains(new Point(1, 3)));
    }

    @Test
    public void doesNotStoreDuplicatePoints() {
        PointSet set = new PointSet();

        set.add(new Point(1, 1));

        assertThat(set.toString()).isEqualTo("(1, 1)");

        set.add(new Point(1, 1));

        assertThat(set.toString()).isEqualTo("(1, 1)");
    }

    @Test
    public void pointSetSupportsEqualityTesting() {
        assertThat(getSet()).isEqualTo(getSet());

        assertThat(getSet(new Point(1, 1))).isNotEqualTo(getSet());

        assertThat(getSet(new Point(1, 1))).isNotEqualTo(getSet(new Point(1, 2)));

        assertThat(getSet(new Point(1, 1), new Point(1, 2))).isEqualTo(
                   getSet(new Point(1, 2), new Point(1, 1)));
    }

    @Test
    public void pointSetSupportsSubtractingAnotherSet() {
        PointSet a = getSet(new Point(1, 1), new Point(1, 2));
        PointSet b = getSet(new Point(1, 1), new Point(1, 3));

        PointSet remainder = a.subtract(b);

        assertThat(a).isEqualTo(getSet(new Point(1, 1), new Point(1, 2)));

        assertThat(remainder).isEqualTo(getSet(new Point(1, 2)));
    }

    @Test
    public void pointSetSupportsIntersectionOperation() {
        PointSet a = getSet(new Point(1, 1), new Point(1, 2));
        PointSet b = getSet(new Point(1, 1), new Point(1, 3));

        PointSet intersection = a.intersect(b);

        assertThat(a).isEqualTo(getSet(new Point(1, 1), new Point(1, 2)));

        assertThat(intersection).isEqualTo(getSet(new Point(1, 1)));
    }

    @Test
    public void setGrowsWhenThereIsNoMoreRoom() {
        PointSet set = new PointSet(2);

        set.add(new Point(1, 1));
        set.add(new Point(2, 1));

        assertThat(getInternalArray(set).length).isEqualTo(2);

        set.add(new Point(3, 1));

        assertThat(getInternalArray(set).length).isEqualTo(4);
    }

    private PointSet getSet(Point... points) {
        PointSet set = new PointSet();
        for (Point point : points) {
            set.add(point);
        }

        return set;
    }

    @Test
    public void nullIsAValidElement() {
        PointSet set = new PointSet();


        set.add(new Point(1, 1));
        set.add(null);
        set.add(new Point(1, 2));

        assertThat(set.toString()).isEqualTo("(1, 1), null, (1, 2)");
    }

    @Test
    public void canRemoveElements() {
        PointSet set = new PointSet();

        set.add(new Point(1, 1));
        set.add(new Point(1, 2));
        set.add(new Point(1, 3));

        set.remove(new Point(1, 4));
        set.remove(new Point(1, 2));

        assertThat(set.toString()).isEqualTo("(1, 1), (1, 3)");
    }

    @SuppressWarnings("PMD")
    private Point[] getInternalArray(PointSet set) {

        Field[] fields = set.getClass().getDeclaredFields();

        List<Field> integerArrayFields = Arrays.stream(fields)
                .filter(field -> field.getType()
                        .equals(Point[].class))
                .toList();

        if (integerArrayFields.isEmpty()) {
            fail("PointSet should have a field of type Point[]");
        }

        if (integerArrayFields.size() > 1) {
            fail("PointSet should have just one field of type Point[]");
        }

        integerArrayFields.getFirst().setAccessible(true);

        try {
            return (Point[]) integerArrayFields.getFirst().get(set);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
