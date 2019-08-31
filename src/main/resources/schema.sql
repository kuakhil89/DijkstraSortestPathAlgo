drop table Planet if exists;

CREATE TABLE Planet (
    planetNode varchar(255) NOT NULL,
    planetName varchar(255) NOT NULL,
    PRIMARY KEY (planetNode)
);

drop table Routes if exists;

CREATE TABLE Routes (
    RouteId int NOT NULL,
    planetOrigin varchar(255) NOT NULL,
    planetDestination varchar(255) NOT NULL,
    distance int NOT NULL,
    PRIMARY KEY (RouteId)
);