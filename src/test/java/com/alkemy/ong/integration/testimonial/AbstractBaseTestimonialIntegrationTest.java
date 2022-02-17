package com.alkemy.ong.integration.testimonial;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.Testimonial;
import com.alkemy.ong.repository.ITestimonialRepository;
import java.sql.Timestamp;
import java.time.Instant;
import org.junit.Before;
import org.springframework.boot.test.mock.mockito.MockBean;

public abstract class AbstractBaseTestimonialIntegrationTest extends AbstractBaseIntegrationTest {

  protected static final Long TESTIMONIAL_ID = 1L;
  protected static final String NAME = "Testimonial name";
  protected static final String CONTENT = "Testimonial content";
  protected static final String PATH = "/testimonials/" + TESTIMONIAL_ID;
  @MockBean
  protected ITestimonialRepository testimonialRepository;

  @Before
  public void checkFindByIdMethod() {
    when(
        testimonialRepository.findByTestimonialIdAndSoftDeleteFalse(eq(TESTIMONIAL_ID))).thenReturn(
        createTestimonialStub());
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
