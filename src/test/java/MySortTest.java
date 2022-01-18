import com.stepan.pet_project.web_student_progress.select_attribute.MySort;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.Sort;

public class MySortTest {

    private static final String argAsc = "Arg asc";
    private static final String argDesc = "Arg desc";

    private static final String multiWordArg = "Arg1 Arg2 arg3 asc";
    private static final String multiWordArgResult = "arg1Arg2Arg3";

    private static final String argOneArgResult = "arg";

    @Test(expected = NullPointerException.class)
    public void ifArgumentNullShouldThrowNullPointerException(){
        MySort.getSortByPassedArgument(null);
    }

    @Test
    public void ifArgEndsAscSortShouldHaveDirectionAsc() {
        Sort sort = MySort.getSortByPassedArgument(argAsc);

        Assert.assertEquals(Sort.by(Sort.Direction.ASC, "arg"), sort);
    }

    @Test
    public void ifArgEndsAscSortShouldHaveDirectionDesc() {
        Sort sort = MySort.getSortByPassedArgument(argDesc);

        Assert.assertEquals(Sort.by(Sort.Direction.DESC, "arg"), sort);
    }

    @Test
    public void ifArgEndsAscAndMultiWordSortShouldHaveConventionArg() {
        Sort sort = MySort.getSortByPassedArgument(multiWordArg);

        Assert.assertEquals(Sort.by(Sort.Direction.ASC, multiWordArgResult), sort);
    }

    @Test
    public void ifArgHaveOneArgSortShouldHaveLoverCaseProperties() {
        Sort sort = MySort.getSortByPassedArgument(argAsc);

        Assert.assertEquals(Sort.by(Sort.Direction.ASC, argOneArgResult), sort);
    }

}
