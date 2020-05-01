  


  //MurmurHash算法 
    HashFunction function= Hashing.murmur3_32();
    HashCode hashCode = function.hashString(longUrl, Charset.forName("utf-8"));
    //i为长url的murmur值
    int i = Math.abs(hashCode.asInt());