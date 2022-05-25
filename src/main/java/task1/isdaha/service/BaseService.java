package task1.isdaha.service;

import task1.isdaha.response.Response;

public interface BaseService <T>{
    Response add(T t);
    Response getById(Long id);
    Response all(int pageSize , int pageNumber);
    Response update(Long id , T t);
    Response deleteById(long id);

}
