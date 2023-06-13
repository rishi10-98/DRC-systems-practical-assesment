# DRC-systems-practical-assesment

we have a  students  endpoint that accepts two optional query parameters  page  and  size . These parameters are used to configure the pagination with  PageRequest . The  fetchAllStudent(pageable)  method of the  studentRepository  returns a  Page  object that contains the requested students paginated according to the  PageRequest .

The  Page  object contains a lot of useful information, such as the total number of pages, total number of elements, current page number, and so on. We can customize the response format of this method according to our needs.
