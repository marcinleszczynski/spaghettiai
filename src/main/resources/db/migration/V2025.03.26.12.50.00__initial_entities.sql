CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "user" (
    id uuid PRIMARY KEY,
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    phone_number character varying(255),
    password character varying(255),
    creation_timestamp timestamp without time zone,
    modification_timestamp timestamp without time zone
);

CREATE TABLE recipe (
    id uuid PRIMARY KEY,
    name character varying(255),
    content text,
    public boolean,
    user_id uuid,
    creation_timestamp timestamp without time zone,
    modification_timestamp timestamp without time zone,
    FOREIGN KEY (user_id) REFERENCES "user"(id)
);

CREATE TABLE rating (
    id uuid PRIMARY KEY,
    recipe_id uuid,
    user_id uuid,
    rating numeric,
    creation_timestamp timestamp without time zone,
    modification_timestamp timestamp without time zone,
    FOREIGN KEY (user_id) REFERENCES "user"(id),
    FOREIGN KEY (recipe_id) REFERENCES recipe(id)
);