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

CREATE TABLE public.COUNTRIES(
    country_uid UUID PRIMARY KEY NOT NULL,
    country_name TEXT,
    language TEXT,
    iso_code TEXT,
    population INT,
    is_eu boolean,

    constraint countries_uq_iso_code unique (iso_code)
);

INSERT INTO public.COUNTRIES (country_uid, country_name, language, iso_code, population, is_eu)
values ('79be8a45-e736-4f89-9fc4-eea931978497', 'lorem', 'impsum', 'EST', 123, true);

CREATE TABLE public.PERSONS (
    person_uid UUID PRIMARY KEY NOT NULL,
    first_name TEXT,
    last_name TEXT,
    gender TEXT,
    country TEXT references public.COUNTRIES(iso_code),
    date_of_birth DATE,

    constraint persons_ck_gender check (gender in ('MALE', 'FEMALE'))
    );

INSERT INTO public.PERSONS (person_uid, first_name, last_name, gender, country, date_of_birth)
values('a15a4b85-884b-41f3-94d5-f154562429f2', 'Jüri', 'Tamm', 'MALE', 'EST', '01.01.1988');

