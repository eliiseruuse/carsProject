CREATE TABLE public.CARS(
    car_uid UUID PRIMARY KEY NOT NULL,
    car_brand TEXT,
    category_sign TEXT,
    engine_power INT,
    color TEXT,
    gearbox TEXT,
    fuel TEXT
);

INSERT INTO public.CARS (car_uid, car_brand, category_sign, engine_power, color, gearbox, fuel)
values ('902e0c83-68bd-488a-b1f4-a698ea8b0708', 'foo', 'bar', 12, 'red', 'MANUAL', 'DIESEL');
