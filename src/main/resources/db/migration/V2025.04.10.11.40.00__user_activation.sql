ALTER TABLE "user"
ADD COLUMN activated boolean default false,
ADD COLUMN activation_code uuid;