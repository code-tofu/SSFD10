package ibf2022.batch2.ssf.frontcontroller.respositories;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationRepository {

    @Autowired @Qualifier("lockout")
    private RedisTemplate<String,String> redisTemplate;
    
    public void disableUserDB(String username) {
        this.redisTemplate.opsForValue().set(username, "LOCKED", Duration.ofMinutes(30));
	}

	public boolean isLockedDB(String username) {
        if(null == this.redisTemplate.opsForValue().get(username)) return false;
		return true;
	}




	// TODO Task 5
	// Use this class to implement CRUD operations on Redis


}
