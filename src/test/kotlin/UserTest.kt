import client.PetStoreClient
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class UserTest {
    val petStoreClient = PetStoreClient()

    @Test
    fun `User login`() {
        val username = "CharlesLebovsky"
        val password = "112233qqWW"

        val response = petStoreClient.User().login(username, password)

        SoftAssertions().apply {
            assertThat(response?.code).`as`("Check response has code 200").isEqualTo(200);
            assertThat(response?.message).`as`("Check message is not null").isNotNull
            assertThat(response?.message).`as`("Check message is not empty").isNotEmpty
        }
    }
}