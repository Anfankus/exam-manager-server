use ExamManager;
drop table if exists `score`;
drop table if exists `solution`;
drop table if exists `exam`;
drop table if exists `user`;
create table `user`
(
    `userid`   varchar(20) not null,
    `username` varchar(10) not null,
    `password` varchar(20) not null,
    `usertype` int         not null,
    primary key (userid) using BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

create table `exam`
(
    `examid`      char(64)                                               not null,
    `examno`      char(64) unique,
    `examname`    varchar(20) charset utf8mb4 COLLATE utf8mb4_0900_ai_ci not null,
    `starttime`   int                                                    not null,
    `examtime`    int                                                    not null,
    `description` varchar(50),
    `totalscore`  int,
    `sq`          json,
    `saq`         json,
    `userid`      varchar(20)                                            not null,
    primary key (examid) using btree,
    constraint foreign key (userid) references user (userid) on update restrict on delete restrict
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

create table `solution`
(
    `solutionid` char(64)    not null,
    `examid`     char(64)    not null,
    `userid`     varchar(20) not null,
    `submittime` int         not null,
    `answer`     json,
    `totalscore` int,
    primary key (solutionid),
    constraint foreign key (examid) references exam (examid) on update restrict on delete restrict,
    constraint foreign key (userid) references user (userid) on update restrict on delete restrict
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

create table `score`
(
    `scoreid`    char(64) not null,
    `solutionid` char(64) not null,
    `value`      json,
    primary key (scoreid),
    constraint foreign key (solutionid) references solution (solutionid) on update restrict on delete restrict
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
