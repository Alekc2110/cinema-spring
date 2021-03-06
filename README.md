# CINEMA-SPRING
## Spring boot project
### *Система-интернет витрина кинотеатра с одним залом.*

Существует две роли: **ADMIN**, **USER**
В системе есть:

+ Расписание показа фильмов на неделю с 8:00 до 22:00 (начало последнего фильма) часа.
+ Незарегистрированный пользователь может видеть список фильмов в прокате, свободные места в зале и имеет возможность зарегистрироваться.
____

 **Зарегистрированному пользователю доступно:**
  + просмотр расписания сеансов на неделю.
  + сортировка записей в таблице с сеансами по дате / времени сеанса.
  + просмотр доступных фильмов, информации по фильму.
  + имеет возможность приобрести билет на выбранный сеанс.
  + просмотреть и отсортировать список купленных билетов
 **Администратор может:**
  + ввести в расписание новый фильм.
  + отменить либо изменить фильм.
  + добавить, удалить, изменить сеанс.
  + посмотреть статистику посещаемости зала в %.
____
UML diagram link: https://zupimages.net/up/21/40/13fc.jpg  
____
  
## To install the project:
git clone https://github.com/Alekc2110/cinema-spring.git
____

## Application launch instructions:
Install database and change your access properties in application.properties
You may use data-mysql.sql to populate your database or use your own
run CinemaSpApplication.class
Go to url localhost:8082/
