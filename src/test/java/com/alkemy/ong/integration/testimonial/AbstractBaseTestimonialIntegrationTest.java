package com.alkemy.ong.integration.testimonial;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.Testimonial;
import com.alkemy.ong.repository.ITestimonialRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


public abstract class AbstractBaseTestimonialIntegrationTest extends AbstractBaseIntegrationTest {

  protected static final Long TESTIMONIAL_ID = 1L;
  protected static final String NAME = "Testimonial name";
  protected static final String CONTENT = "Testimonial content";
  protected static final String PATH = "/testimonials";
  protected static final String PATH_ID = PATH + "/" + TESTIMONIAL_ID;

  @MockBean
  protected ITestimonialRepository testimonialRepository;

  @Before
  public void checkFindMethod() {
    when(testimonialRepository.findBySoftDeleteFalseOrderByTimestampDesc(any(Pageable.class)))
        .thenReturn(buildTestimonialStubPage());
  }

  protected Page<Testimonial> buildTestimonialStubPage() {
    List<Testimonial> testimonials = new ArrayList<>();
    testimonials.add(createTestimonialStub());
    return new PageImpl<>(testimonials);

  }

  protected Testimonial createTestimonialStub() {
    Testimonial testimonial = new Testimonial();
    testimonial.setName(NAME);
    testimonial.setContent(CONTENT);
    testimonial.setSoftDelete(false);
    testimonial.setTimestamp(Timestamp.from(Instant.now()));
    return testimonial;
  }
}
