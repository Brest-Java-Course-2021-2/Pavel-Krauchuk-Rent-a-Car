Функциональная спецификация программы "Прокат автомобилей" v1.0  от 06.11.2021

Прокат автомобилей

Видение
Требуется Web приложение для работы с клиентами при аренде автобилей.
Приложение должно реализовывать следущие функции:
- Клиент выбирает Автомобиль из списка доступных.
- Заполняет форму Заказа, указывая паспортные данные, срок аренды.
- Клиент оплачивает Заказ.
- Администратор регистрирует возврат автомобиля.
- В случае повреждения Автомобиля Администратор вносит информацию и выставляет счет за ремонт.
- Администратор может отклонить Заявку, указав причины отказа.

1. Транспортные средства
1.1. Список транспорных средств

Данный режим работы программы предназначен для просмотра потенциальными арендатарами списка
доступных для аренды транспорных стредств.

Основной сценарий:
- Пользователь выбирает пункт меню "Аренда транспортных средств".
- Отображается форма просмотра доступных транспортных средств.

В списке отображаются список транспортых средств имеющихся в наличии и доступных к аренде.
- возможные модели, цвет, год выпуска.

1.2. Выбор транспортного средства.
Данный режим предназначается для оформения заявки на аренду и получения счета для оплаты.

Основной сценарий:
- Польззователь находясь в режиме просмотра списка доступных ролей нажимает кнопку "Заказать";
- Отабражается форма для внесения персональных данных клиента, необходимых для осуществления аренды;
- После заполнения необходимых данных пользователь нажимает кнопку "Арендовать";
- Если данные введены не корректно, то выводится сообщение об ошибке;
- Если данные введены корректно, то формируется заявка и выставляется счет для оплаты, заявка сохраняется в базу данных;
- Нажав кнопку отмена пользователь возвращается к списку транспортных средств.

2. Администратор
2.1. Просмотр списка траспорных средств
Данный режим работы предназначен для работы административного персонала и контроля за текущим состоянием по аренде транспортных средств.

Основной сценарий:
- Пользователю отображается весь список транспортных средств с отметкой заявление/арендовано/в гараже.

2.2. Рассмотрение заявки.
Основной сценарий:
- Пользователь находясь в режиме просмотра списка транспортных средств нажимает кнопку "Заявка";
- Отображается форма заказа заполненая клиентом. Пользователь проверяет правильность заполнения данных;
- Нажатие кнопки "Подтвердить заявку" возвражает пользователя к списку транспортных средств. Статус указанного транспортного \
средства в базе нанных меняется на "Арендовано";
- При нажатии кнопки "Отказать" открывается форма для указания причин отказа;
- Нажатие кнопки "Отмена" возвращает пользователя к списку транспортных средств.

2.3. Возврат автомобиля.

Основной сценарий:
- Пользователь находясь в режиме просмотра списка транспортных средств нажимает кнопку "Арендовано";
- Открывается форма для регистрации возврата автомобиля;
- Пользователь указывает состояние автомобиля, в случае необходимости вносит данные о необходимом ремонте и сумму затрат на него;
- Нажатие кнопки "Сохранить" возвращает к просмотру списка транспортных средств. В базу данных заносится отметка о возврате
транспотного средства;
- Нажатие кнопки "Отмена" возвражает к просмотру списка транспортных средств. Данные не сохнаняются.




