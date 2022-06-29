package cu.hash.storeexercise;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StoreExerciseApplicationTests {

	@Test
	void contextLoads() {
		assertThat(this.getClass()).isEqualTo(this.getClass());
	}

}
