local key = KEYS[1] --限流key（一秒一个）
local limit = tonumber(ARGV[1]) --限流大小
local current = tonumber(redis.call('get', key) or "0")
if current +1 > limit then --如果超出限流大小
    return 0
else --请求书+1.并设置两秒过期
   redis.call("INCRBY", key, "1")
   redis.call("expire", key, "2")
end
return 1;