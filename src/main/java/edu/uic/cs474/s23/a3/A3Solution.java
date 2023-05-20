package edu.uic.cs474.s23.a3;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class A3Solution implements StreamProcessor {

    @Override
    public List<Integer> problem1(List<Integer> input) {
        return input.stream().filter(n -> n > 0).collect(Collectors.toList());
    }

    @Override
    public Map<Integer, HasId> problem2(List<HasId> input) {
        return input.stream().collect(Collectors.toMap(e->e.getId(),e->e));
    }

    @Override
    public Set<String> problem3(String[] array1, String[] array2) {
        List<String> l1 = Arrays.asList(array1);
        List<String> l2 = Arrays.asList(array2);
        return l1.stream().distinct().filter(l2::contains).collect(Collectors.toSet());
    }

    @Override
    public Map<String, Long> problem4(List<List<String>> input) {
        return input.stream().flatMap(list -> list.stream()).collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    }

    @Override
    public Set<Course> problem5(List<Semester> input) {
        return input.stream().flatMap(s->s.courses.stream()).distinct().filter(c -> c.students > 20).collect(Collectors.toSet());
    }

    @Override
    public Map<String, Set<String>> problem6(List<ClassOrInterfaceDeclaration> input) {
        // Function that returns the set of class names that have a method called m
        Function<MethodDeclaration,Stream<String>> f = (m -> input.stream().filter(c->c.getMethods().stream().map(d->d.getNameAsString()).anyMatch(d->d.equals(m.getNameAsString()))).map(c->c.getNameAsString()));
        return input.stream().collect(Collectors.toMap(c->c.getNameAsString(), c->c.getMethods().stream().flatMap(f).filter(n->!n.equals(c.getNameAsString())).collect(Collectors.toSet())));
    }
}
