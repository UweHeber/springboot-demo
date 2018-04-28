package it.heber.sandbox.springbootdemo.web.util;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * A {@link ResourceAssembler} that focuses purely on the domain type,
 * returning back {@link Resource} for that type instead of
 * {@link org.springframework.hateoas.ResourceSupport}.
 *
 * @param <T> Domain type for which this {@link ResourceAssembler} should be generated
 *
 * @author Uwe Heber <uwe@heber.it>
 * @since 1.0
 */
public class SimpleResourceAssembler<T> implements ResourceAssembler<T, Resource<T>> {

    private final Class<?> controllerClass;

    public SimpleResourceAssembler(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
    }

    /**
     * Converts the given entity into a {@link Resource}.
     *
     * @param entity
     * @return
     */
    @Override
    public Resource<T> toResource(T entity) {
        Resource<T> resource = new Resource<T>(entity);
        addLinks(resource);
        return resource;
    }

    /**
     * Define links to add to every {@link Resource}.
     *
     * @param resource
     */
    public void addLinks(Resource<T> resource) {
        resource.add(linkTo(controllerClass).slash(resource.getContent()).withSelfRel());
    }
}
