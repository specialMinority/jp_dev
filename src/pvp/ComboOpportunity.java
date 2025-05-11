package pvp;

import java.util.Scanner;

/**
 * 이 게임은 2D PVP Fighting 게임으로 x축, y축, z축이 존재한다.
 * 모든 캐릭터의 체력은 100
 * 상대에게 데미지를 주려면 스킬을 사용해 공중으로 띄워야 한다.
 * 중력 보정이 존재해 한 번의 콤보로 소모시킬 수 있는 상대의 체력은 최대 49이다.
 * 스킬 분류:
 * - 궁극기 (1개): 데미지 30 / 콤보용 / 쿨타임이 매우 긴 편
 * - 주요 스킬 (2개): 데미지 20 / 기회 잡기 또는 콤보용. 슈퍼 아머 띄우기 판정. / 쿨타임이 긴 편
 * - 일반 스킬 (4개): 데미지 10 / 기회잡기도 가능하나 슈퍼 아머가 없는 띄우기 판정. 방어+콤보용 / 쿨타임이 매우 짧아서 어떤 경우에도 2개 이상은 사용 가능
 * - 평타 : 데미지1 / 콤보용 / 쿨타임 없음
 * - 피격기 : 모든 캐릭터는 1라운드 당 한 번 피격 도중에 공중에서 탈출할 수 있는 이동기를 가지고 있다. 피격기로 이동 직후에는 스킬을 사용할 수 없다.
 * 스킬의 유지 시간은 모두 같다.(주요 스킬의 슈퍼 아머의 유지 시간과는 다른 개념.) 평타 0.2초 / 일반 스킬 0.5초 / 주요 스킬 1초 / 궁극기 2초
 * 스킬의 범위는 모두 같다. 평타 30px / 일반 스킬 60px / 주요 스킬 150px / 궁극기 300px
 * 스킬의 쿨타임은 모두 같다.
 * 슈퍼 아머란 캐릭터가 피격받아도 띄워지지 않는 상태이다.
 * 주요 스킬은 캐릭터마다 판정의 우위가 존재한다. 슈퍼아머의 유지시간이 더 길 수록 판정이 좋다. 슈퍼 아머의 유지시간은 해당 스킬의 유지시간을 초과해서 유지되지는 않는다.
 * 상대의 주요 스킬에 반격할 수 있는 유일한 수단은 같이 주요 스킬을 사용하는 것이다. 슈퍼아머 판정이 있기 때문에 피격 당하더라도 버틸 수 있기 때문.
 * 스킬의 판정과는 상관없이 스킬의 유지시간과 범위를 이용해서 상대방을 견제할 수 있다.
 * 콤보를 느리게 할 수록 회피율이 증가한다. 회피율이 올라가서 miss가 발생하면 콤보 기회를 놓친다.
 * 모든 스킬의 시전 범위는 y축 기준으로 위보다 아래로 더 넓게 설계되어 있다. 일반 스킬의 전체 범위는 주요 스킬의 범위보다 작지만 y축 아래의 범위는 주요 스킬보다 넓다.  따라서 좋은 포지션은 상대방 캐릭터보다 y축 기준으로 더 위에 있는 경우다.
 * 1라운드 당 게임 시간 총 3분이며, 게임 시간이 끝나면 데미지를 더 많이 준 쪽이 승리한다.
 * 모든 캐릭터는 리스크를 지양하는 플레이를 한다.
 * 모든 캐릭터는 게임 승리를 목표로 플레이한다.
 */

public class ComboOpportunity {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("남은 게임 시간이 1분 미만인가?");
        System.out.println("1. 1분 미만 2. 1분 이상");
        String timeLeft = sc.nextLine();
        if (timeLeft.equals("1")) {
            System.out.println("내 캐릭터가 준 데미지가 상대방이 준 데미지보다 높은가?");
            System.out.println("1. 더 높다 2. 더 적다");
            String damage = sc.nextLine();
            if (damage.equals("1")) {
                System.out.println("상대방의 주요 스킬 중 몇 개가 쿨타임인가?");
                System.out.println("1. 0개 2. 1개 3. 2개");
                String opponentMainSkillCooldown = sc.nextLine();
                if (opponentMainSkillCooldown.equals("1")) {
                    System.out.println("내 캐릭터의 주요 스킬 중 몇 개가 쿨타임인가?");
                    System.out.println("1. 0개 2. 1개 3. 2개");
                    String mySkillCooldown = sc.nextLine();
                    if (mySkillCooldown.equals("1")) {
                        System.out.println("상대에게 콤보 기회를 허용하면 패배가 확정되는가?");
                        System.out.println("1. 그렇다 2. 아니다 ");
                        String opponentOpportunity = sc.nextLine();
                        if (opponentOpportunity.equals("1")) {
                            System.out.println("내 캐릭터의 포지션은 어떤가?");
                            System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                            String position = sc.nextLine();
                            if (position.equals("1")) {
                                System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                                System.out.println("1. 좋다 2. 안 좋다");
                                String hitbox = sc.nextLine();
                                if (hitbox.equals("1")) {
                                    System.out.println("좋은 포지션을 유지하며 주요 스킬들을 견제용으로 사용하며 시간을 끄는 플레이를 한다.");
                                } else if (hitbox.equals("2")) {
                                    System.out.println("좋은 포지션을 유지하며 주요 스킬로 진입해서 기회를 잡는다.");
                                }
                            } else if (position.equals("2")) {
                                System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                                System.out.println("1. 좋다 2. 안 좋다");
                                String hitbox = sc.nextLine();
                                if (hitbox.equals("1")) {
                                    System.out.println("안 좋은 포지션에서 빠르게 벗어나고, 주요 스킬들을 견제용으로 사용하며 시간을 끈다.");
                                } else if (hitbox.equals("2")) {
                                    System.out.println("안 좋은 포지션에서 빠르게 벗어나고, 주요 스킬들로 진입해서 기회를 잡는다.");
                                }
                            }
                        } else if (opponentOpportunity.equals("2")) {
                            System.out.println("내 캐릭터의 포지션은 어떤가?");
                            System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                            String position = sc.nextLine();
                            if (position.equals("1")) {
                                System.out.println("좋은 포지션을 유지하는 데에 집중하며 주요 스킬들과 일반 스킬들로 견제하며 시간을 끄는 플레이를 한다.");
                            } else if (position.equals("2")) {
                                System.out.println("안 좋은 포지션으로부터 벗어나는 데에 집중하며 주요 스킬들과 일반 스킬들을 견제용으로 사용하며 시간을 끄는 플레이를 한다.");
                            }
                        }
                    } else if (mySkillCooldown.equals("2")) {
                        System.out.println("상대에게 콤보 기회를 허용하면 패배가 확정되는가?");
                        System.out.println("1. 그렇다 2. 아니다");
                        String opponentOpportunity = sc.nextLine();
                        if (opponentOpportunity.equals("1")) {
                            System.out.println("내 캐릭터의 포지션은 어떤가?");
                            System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                            String position = sc.nextLine();
                            if (position.equals("1")) {
                                System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                                System.out.println("1. 좋다 2. 안 좋다");
                                String hitbox = sc.nextLine();
                                if (hitbox.equals("1")) {
                                    System.out.println("좋은 포지션을 유지하며 주요 스킬과 일반 스킬들로 견제하며 시간을 끄는 플레이를 한다.");
                                } else if (hitbox.equals("2")) {
                                    System.out.println("좋은 포지션을 유지하며 주요 스킬로 진입해서 기회를 잡는다.");
                                }
                            } else if (position.equals("2")) {
                                System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                                System.out.println("1. 좋다 2. 안 좋다");
                                String hitbox = sc.nextLine();
                                if (hitbox.equals("1")) {
                                    System.out.println("안 좋은 포지션으로부터 벗어나는 데에 집중하며 주요 스킬과 일반 스킬들로 견제하며 시간을 끄는 플레이를 한다.");
                                } else if (hitbox.equals("2")) {
                                    System.out.println("안 좋은 포지션에서 빠르게 벗어나고, 주요 스킬로 진입해서 기회를 잡는다.");
                                }
                            }
                        } else if (opponentOpportunity.equals("2")) {
                            System.out.println("내 캐릭터의 포지션은 어떤가?");
                            System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                            String position = sc.nextLine();
                            if (position.equals("1")) {
                                System.out.println("좋은 포지션을 유지하는 데에 집중하며 주요 스킬과 일반 스킬들로 견제하며 시간을 끄는 플레이를 한다.");
                            } else if (position.equals("2")) {
                                System.out.println("안 좋은 포지션으로부터 벗어나는 데에 집중하며 주요 스킬과 일반 스킬들로 견제하며 시간을 끄는 플레이를 한다.");
                            }
                        }
                    } else if (mySkillCooldown.equals("3")) {
                        System.out.println("내 캐릭터의 포지션은 어떤가?");
                        System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                        String position = sc.nextLine();
                        if (position.equals("1")) {
                            System.out.println("좋은 포지션을 유지하는 데에 집중하며 일반 스킬들로 견제하며 시간을 끄는 플레이를 한다.");
                        } else if (position.equals("2")) {
                            System.out.println("안 좋은 포지션으로부터 벗어나는 데에 집중하며 일반 스킬들로 견제하며 시간을 끄는 플레이를 한다.");
                        }
                    }
                } else if (opponentMainSkillCooldown.equals("2")) {
                    System.out.println("내 캐릭터의 주요 스킬 중 몇 개가 쿨타임인가?");
                    System.out.println("1. 0개 2. 1개 3. 2개");
                    String mySkillCooldown = sc.nextLine();
                    if (mySkillCooldown.equals("1")) {
                        System.out.println("상대에게 콤보 기회를 허용하면 패배가 확정되는가?");
                        System.out.println("1. 그렇다 2. 아니다");
                        String opponentOpportunity = sc.nextLine();
                        if (opponentOpportunity.equals("1")) {
                            System.out.println("내 캐릭터의 포지션은 어떤가?");
                            System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                            String position = sc.nextLine();
                            if (position.equals("1")) {
                                System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                                System.out.println("1. 좋다 2. 안 좋다");
                                String hitbox = sc.nextLine();
                                if (hitbox.equals("1")) {
                                    System.out.println("좋은 포지션에서 압박하며 주요 스킬 하나를 소모해 상대의 주요 스킬 사용을 유도하고 진입해서 기회를 잡는다.");
                                } else if (hitbox.equals("2")) {
                                    System.out.println("주요 스킬들로 빠르게 진입해서 기회를 잡는다.");
                                }
                            } else if (position.equals("2")) {
                                System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                                System.out.println("1. 좋다 2. 안 좋다");
                                String hitbox = sc.nextLine();
                                if (hitbox.equals("1")) {
                                    System.out.println("안 좋은 포지션에서 빠르게 벗어나고, 주요 스킬 하나를 소모해 상대의 주요 스킬 사용을 유도하고 진입해서 기회를 잡는다.");
                                } else if (hitbox.equals("2")) {
                                    System.out.println("안 좋은 포지션에서 빠르게 벗어나고, 주요 스킬들로 진입해서 기회를 잡는다.");
                                }
                            }
                        } else if (opponentOpportunity.equals("2")) {
                            System.out.println("내 캐릭터의 포지션은 어떤가?");
                            System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                            String position = sc.nextLine();
                            if (position.equals("1")) {
                                System.out.println("좋은 포지션을 유지하는 데에 집중하며 주요 스킬들과 일반 스킬들로 견제하며 시간을 끄는 플레이를 한다.");
                            } else if (position.equals("2")) {
                                System.out.println("안 좋은 포지션으로부터 벗어나는 데에 집중하며 주요 스킬들과 일반 스킬들을 견제용으로 사용하며 시간을 끄는 플레이를 한다.");
                            }
                        }
                    } else if (mySkillCooldown.equals("2")) {
                        System.out.println("상대에게 콤보 기회를 허용하면 패배가 확정되는가?");
                        System.out.println("1. 그렇다 2. 아니다");
                        String opponentOpportunity = sc.nextLine();
                        if (opponentOpportunity.equals("1")) {
                            System.out.println("내 캐릭터의 포지션은 어떤가?");
                            System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                            String position = sc.nextLine();
                            if (position.equals("1")) {
                                System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                                System.out.println("1. 좋다 2. 안 좋다");
                                String hitbox = sc.nextLine();
                                if (hitbox.equals("1")) {
                                    System.out.println("좋은 포지션을 유지하며 주요 스킬로 상대의 주요 스킬을 소모시키고 일반 스킬들로 진입해서 기회를 잡는다.");
                                } else if (hitbox.equals("2")) {
                                    System.out.println("좋은 포지션을 유지하며 주요 스킬로 진입해서 기회를 잡는다.");
                                }
                            } else if (position.equals("2")) {
                                System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                                System.out.println("1. 좋다 2. 안 좋다");
                                String hitbox = sc.nextLine();
                                if (hitbox.equals("1")) {
                                    System.out.println("안 좋은 포지션에서 빠르게 벗어나 주요 스킬로 상대의 주요 스킬을 소모시키고 일반 스킬들로 진입해서 기회를 잡는다.");
                                } else if (hitbox.equals("2")) {
                                    System.out.println("안 좋은 포지션에서 빠르게 벗어나 주요 스킬로 진입해서 기회를 잡는다.");
                                }
                            }
                        }
                    } else if (mySkillCooldown.equals("3")) {
                        System.out.println("상대에게 콤보 기회를 허용하면 패배가 확정되는가?");
                        System.out.println("1. 그렇다 2. 아니다");
                        String opponentOpportunity = sc.nextLine();
                        if (opponentOpportunity.equals("1")) {
                            System.out.println("내 캐릭터의 포지션은 어떤가?");
                            System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                            String position = sc.nextLine();
                            if (position.equals("1")) {
                                System.out.println("좋은 포지션으로 압박하며 일반 스킬들로 상대의 주요 스킬을 소모시키고 일반 스킬들로 진입해서 기회를 잡는다.");
                            } else if (position.equals("2")) {
                                System.out.println("안 좋은 포지션에서 빠르게 벗어나 일반 스킬들로 상대의 주요 스킬을 소모시키고 일반 스킬들로 진입해서 기회를 잡는다.");
                            }
                        } else if (opponentOpportunity.equals("2")) {
                            System.out.println("내 캐릭터의 포지션은 어떤가?");
                            System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                            String position = sc.nextLine();
                            if (position.equals("1")) {
                                System.out.println("좋은 포지션을 유지하는 데에 집중하며 일반 스킬들로 견제하며 시간을 끄는 플레이를 한다.");
                            } else if (position.equals("2")) {
                                System.out.println("안 좋은 포지션으로부터 벗어나는 데에 집중하며 일반 스킬들로 견제하며 시간을 끄는 플레이를 한다.");
                            }
                        }
                    }
                } else if (opponentMainSkillCooldown.equals("3")) {
                    System.out.println("내 캐릭터의 주요 스킬 중 몇 개가 쿨타임인가?");
                    System.out.println("1. 1개 이하 2. 2개");
                    String mySkillCooldown = sc.nextLine();
                    if (mySkillCooldown.equals("1")) {
                        System.out.println("내 캐릭터의 포지션은 어떤가?");
                        System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                        String position = sc.nextLine();
                        if (position.equals("1")) {
                            System.out.println("좋은 포지션을 유지하며 주요 스킬로 빠르게 진입해서 기회를 잡는다.");
                        } else if (position.equals("2")) {
                            System.out.println("안 좋은 포지션에서 빠르게 벗어나 주요 스킬로 빠르게 진입해서 기회를 잡는다.");
                        }
                    } else if (mySkillCooldown.equals("2")) {
                        System.out.println("내 캐릭터의 포지션은 어떤가?");
                        System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                        String position = sc.nextLine();
                        if (position.equals("1")) {
                            System.out.println("좋은 포지션을 유지하며 일반 스킬들로 빠르게 진입해서 기회를 잡는다.");
                        } else if (position.equals("2")) {
                            System.out.println("안 좋은 포지션에서 빠르게 벗어나 일반 스킬들로 빠르게 진입해서 기회를 잡는다.");
                        }
                    }
                }
            }
            if (damage.equals("2")) {
                System.out.println("상대방의 주요 스킬 중 몇 개가 쿨타임인가?");
                System.out.println("1. 0개 2. 1개 3. 2개");
                String opponentMainSkillCooldown = sc.nextLine();
                if (opponentMainSkillCooldown.equals("1")) {
                    System.out.println("내 캐릭터의 주요 스킬 중 몇 개가 쿨타임인가?");
                    System.out.println("1. 0개 2. 1개 3. 2개");
                    String mySkillCooldown = sc.nextLine();
                    if (mySkillCooldown.equals("1")) {
                        System.out.println("내 캐릭터의 포지션은 어떤가?");
                        System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                        String position = sc.nextLine();
                        if (position.equals("1")) {
                            System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                            System.out.println("1. 좋다 2. 안 좋다");
                            String hitbox = sc.nextLine();
                            if (hitbox.equals("1")) {
                                System.out.println("좋은 포지션으로 압박하며 주요 스킬들로 상대의 주요 스킬들을 소모시키고 일반 스킬들로 진입해서 기회를 잡는다.");
                            } else if (hitbox.equals("2")) {
                                System.out.println("좋은 포지션을 유지하며 주요 스킬들로 진입해서 기회를 잡는다.");
                            }
                        } else if (position.equals("2")) {
                            System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                            System.out.println("1. 좋다 2. 안 좋다");
                            String hitbox = sc.nextLine();
                            if (hitbox.equals("1")) {
                                System.out.println("안 좋은 포지션에서 빠르게 벗어나고 주요 스킬들로 상대의 주요 스킬들을 소모시키고 일반 스킬들로 진입해서 기회를 잡는다.");
                            } else if (hitbox.equals("2")) {
                                System.out.println("안 좋은 포지션에서 빠르게 벗어나고 주요 스킬들로 진입해서 기회를 잡는다.");
                            }
                        }
                    } else if (mySkillCooldown.equals("2")) {
                        System.out.println("내 캐릭터의 포지션은 어떤가?");
                        System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                        String position = sc.nextLine();
                        if (position.equals("1")) {
                            System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                            System.out.println("1. 좋다 2. 안 좋다");
                            String hitbox = sc.nextLine();
                            if (hitbox.equals("1")) {
                                System.out.println("좋은 포지션으로 압박하며 주요 스킬과 일반 스킬들로 상대의 주요 스킬들을 소모시키고 일반 스킬들로 진입해서 기회를 잡는다.");
                            } else if (hitbox.equals("2")) {
                                System.out.println("좋은 포지션을 유지하며 주요 스킬로 진입해서 기회를 잡는다.");
                            }
                        } else if (position.equals("2")) {
                            System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                            System.out.println("1. 좋다 2. 안 좋다");
                            String hitbox = sc.nextLine();
                            if (hitbox.equals("1")) {
                                System.out.println("안 좋은 포지션에서 빠르게 벗어나고 주요 스킬과 일반 스킬들로 상대의 주요 스킬들을 소모시키고 일반 스킬들로 진입해서 기회를 잡는다.");
                            } else if (hitbox.equals("2")) {
                                System.out.println("안 좋은 포지션에서 빠르게 벗어나고 주요 스킬로 진입해서 기회를 잡는다.");
                            }
                        }
                    } else if (mySkillCooldown.equals("3")) {
                        System.out.println("내 캐릭터의 포지션은 어떤가?");
                        System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                        String position = sc.nextLine();
                        if (position.equals("1")) {
                            System.out.println("좋은 포지션으로 압박하며 일반 스킬들로 상대의 주요 스킬들을 소모시키고 일반 스킬들로 진입해서 기회를 잡는다.");
                        } else if (position.equals("2")) {
                            System.out.println("안 좋은 포지션에서 빠르게 벗어나 일반 스킬들로 상대의 주요 스킬들을 소모시키고 일반 스킬들로 진입해서 기회를 잡는다.");
                        }
                    }
                } else if (opponentMainSkillCooldown.equals("2")) {
                    System.out.println("내 캐릭터의 주요 스킬 중 몇 개가 쿨타임인가?");
                    System.out.println("1. 0개 2. 1개 3. 2개");
                    String mySkillCooldown = sc.nextLine();
                    if (mySkillCooldown.equals("1")) {
                        System.out.println("내 캐릭터의 포지션은 어떤가?");
                        System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                        String position = sc.nextLine();
                        if (position.equals("1")) {
                            System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                            System.out.println("1. 좋다 2. 안 좋다");
                            String hitbox = sc.nextLine();
                            if (hitbox.equals("1")) {
                                System.out.println("좋은 포지션으로 압박하며 주요 스킬로 상대의 주요 스킬을 소모시키고 주요 스킬로 진입해서 기회를 잡는다.");
                            } else if (hitbox.equals("2")) {
                                System.out.println("좋은 포지션을 유지하며 주요 스킬들로 진입해서 기회를 잡는다.");
                            }
                        } else if (position.equals("2")) {
                            System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                            System.out.println("1. 좋다 2. 안 좋다");
                            String hitbox = sc.nextLine();
                            if (hitbox.equals("1")) {
                                System.out.println("안 좋은 포지션에서 빠르게 벗어나 주요 스킬로 상대의 주요 스킬을 소모시키고 주요 스킬로 진입해서 기회를 잡는다.");
                            } else if (hitbox.equals("2")) {
                                System.out.println("안 좋은 포지션에서 빠르게 벗어나 주요 스킬들로 진입해서 기회를 잡는다.");
                            }
                        }
                    } else if (mySkillCooldown.equals("3")) {
                        System.out.println("내 캐릭터의 포지션은 어떤가?");
                        System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                        String position = sc.nextLine();
                        if (position.equals("1")) {
                            System.out.println("좋은 포지션으로 압박하며 일반 스킬들로 상대의 주요 스킬을 소모시키고 일반 스킬들로 진입해서 기회를 잡는다.");
                        } else if (position.equals("2")) {
                            System.out.println("안 좋은 포지션에서 빠르게 벗어나 일반 스킬들로 상대의 주요 스킬을 소모시키고 일반 스킬들로 진입해서 기회를 잡는다.");
                        }
                    }
                } else if (opponentMainSkillCooldown.equals("3")) {
                    System.out.println("내 캐릭터의 주요 스킬 중 몇 개가 쿨타임인가?");
                    System.out.println("1. 0개 2. 1개 3. 2개");
                    String mySkillCooldown = sc.nextLine();
                    if (mySkillCooldown.equals("1")) {
                        System.out.println("내 캐릭터의 포지션은 어떤가?");
                        System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                        String position = sc.nextLine();
                        if (position.equals("1")) {
                            System.out.println("좋은 포지션을 유지하며 주요 스킬들로 진입해서 기회를 잡는다.");
                        } else if (position.equals("2")) {
                            System.out.println("안 좋은 포지션에서 빠르게 벗어나 주요 스킬들로 진입해서 기회를 잡는다.");
                        }
                    } else if (mySkillCooldown.equals("2")) {
                        System.out.println("내 캐릭터의 포지션은 어떤가?");
                        System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                        String position = sc.nextLine();
                        if (position.equals("1")) {
                            System.out.println("좋은 포지션을 유지하며 주요 스킬로 진입해서 기회를 잡는다.");
                        } else if (position.equals("2")) {
                            System.out.println("안 좋은 포지션에서 빠르게 벗어나 주요 스킬로 진입해서 기회를 잡는다.");
                        }
                    } else if (mySkillCooldown.equals("3")) {
                        System.out.println("내 캐릭터의 포지션은 어떤가?");
                        System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                        String position = sc.nextLine();
                        if (position.equals("1")) {
                            System.out.println("좋은 포지션을 유지하며 일반 스킬들로 진입해서 기회를 잡는다.");
                        } else if (position.equals("2")) {
                            System.out.println("안 좋은 포지션에서 빠르게 벗어나 일반 스킬들로 진입해서 기회를 잡는다.");
                        }
                    }
                }
            }
        } else if (timeLeft.equals("2")) {
            System.out.println("상대방의 주요 스킬 중 몇 개가 쿨타임인가?");
            System.out.println("1. 0개 2. 1개 3. 2개");
            String opponentMainSkillCooldown = sc.nextLine();
            if (opponentMainSkillCooldown.equals("1")) {
                System.out.println("내 캐릭터의 주요 스킬 중 몇 개가 쿨타임인가?");
                System.out.println("1. 0개 2. 1개 3. 2개");
                String mySkillCooldown = sc.nextLine();
                if (mySkillCooldown.equals("1")) {
                    System.out.println("내 캐릭터의 포지션은 어떤가?");
                    System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                    String position = sc.nextLine();
                    if (position.equals("1")) {
                        System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                        System.out.println("1. 좋다 2. 안 좋다");
                        String hitbox = sc.nextLine();
                        if (hitbox.equals("1")) {
                            System.out.println("좋은 포지션으로 압박하며 일반 스킬들로 상대의 주요 스킬들을 소모시키고 주요 스킬들로 진입해서 기회를 잡는다.");
                        } else if (hitbox.equals("2")) {
                            System.out.println("좋은 포지션을 유지하며 주요 스킬들로 진입해서 기회를 잡는다.");
                        }
                    } else if (position.equals("2")) {
                        System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                        System.out.println("1. 좋다 2. 안 좋다");
                        String hitbox = sc.nextLine();
                        if (hitbox.equals("1")) {
                            System.out.println("안 좋은 포지션에서 벗어나는 데에 집중하며 상위 포지션에서 일반 스킬들로 상대의 주요 스킬들을 소모시키고 주요 스킬들로 진입해서 기회를 잡는다.");
                        } else if (hitbox.equals("2")) {
                            System.out.println("안 좋은 포지션에서 벗어나는 데에 집중하며 상위 포지션에서 주요 스킬들로 진입해서 기회를 잡는다.");
                        }
                    }
                } else if (mySkillCooldown.equals("2")) {
                    System.out.println("내 캐릭터의 포지션은 어떤가?");
                    System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                    String position = sc.nextLine();
                    if (position.equals("1")) {
                        System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                        System.out.println("1. 좋다 2. 안 좋다");
                        String hitbox = sc.nextLine();
                        if (hitbox.equals("1")) {
                            System.out.println("좋은 포지션으로 압박하며 일반 스킬들로 상대의 주요 스킬들을 소모시키고 주요 스킬로 진입해서 기회를 잡는다.");
                        } else if (hitbox.equals("2")) {
                            System.out.println("좋은 포지션을 유지하며 주요 스킬로 진입해서 기회를 잡는다.");
                        }
                    } else if (position.equals("2")) {
                        System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                        System.out.println("1. 좋다 2. 안 좋다");
                        String hitbox = sc.nextLine();
                        if (hitbox.equals("1")) {
                            System.out.println("안 좋은 포지션에서 벗어나는 데에 집중하며 상위 포지션에서 일반 스킬들로 상대의 주요 스킬들을 소모시키고 주요 스킬로 진입해서 기회를 잡는다.");
                        } else if (hitbox.equals("2")) {
                            System.out.println("안 좋은 포지션에서 벗어나는 데에 집중하며 상위 포지션에서 주요 스킬로 진입해서 기회를 잡는다.");
                        }
                    }
                } else if (mySkillCooldown.equals("3")) {
                    System.out.println("내 캐릭터의 포지션은 어떤가?");
                    System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                    String position = sc.nextLine();
                    if (position.equals("1")) {
                        System.out.println("좋은 포지션으로 압박하며 일반 스킬들로 상대의 주요 스킬들을 소모시키고, 주요 스킬의 쿨타임을 기다렸다가 진입해서 기회를 잡는다.");
                    } else if (position.equals("2")) {
                        System.out.println("안 좋은 포지션에서 벗어나는 데에 집중하며 상위 포지션에서 일반 스킬들로 상대의 주요 스킬들을 소모시키고, 주요 스킬의 쿨타임을 기다렸다가 진입해서 기회를 잡는다.");
                    }
                }
            } else if (opponentMainSkillCooldown.equals("2")) {
                System.out.println("내 캐릭터의 주요 스킬 중 몇 개가 쿨타임인가?");
                System.out.println("1. 0개 2. 1개 3. 2개");
                String mySkillCooldown = sc.nextLine();
                if (mySkillCooldown.equals("1")) {
                    System.out.println("내 캐릭터의 포지션은 어떤가?");
                    System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                    String position = sc.nextLine();
                    if (position.equals("1")) {
                        System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                        System.out.println("1. 좋다 2. 안 좋다");
                        String hitbox = sc.nextLine();
                        if (hitbox.equals("1")) {
                            System.out.println("좋은 포지션으로 압박하며 주요 스킬로 상대의 주요 스킬을 소모시키고, 주요 스킬로 진입해서 기회를 잡는다.");
                        } else if (hitbox.equals("2")) {
                            System.out.println("좋은 포지션을 유지하며 주요 스킬들로 진입해서 기회를 잡는다.");
                        }
                    } else if (position.equals("2")) {
                        System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                        System.out.println("1. 좋다 2. 안 좋다");
                        String hitbox = sc.nextLine();
                        if (hitbox.equals("1")) {
                            System.out.println("안 좋은 포지션에서 벗어나는 데에 집중하며 상위 포지션에서 주요 스킬로 상대의 주요 스킬을 소모시키고, 주요 스킬로 진입해서 기회를 잡는다.");
                        } else if (hitbox.equals("2")) {
                            System.out.println("안 좋은 포지션에서 벗어나는 데에 집중하며 상위 포지션에서 주요 스킬들로 진입해서 기회를 잡는다.");
                        }
                    }
                } else if (mySkillCooldown.equals("2")) {
                    System.out.println("내 캐릭터의 포지션은 어떤가?");
                    System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                    String position = sc.nextLine();
                    if (position.equals("1")) {
                        System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                        System.out.println("1. 좋다 2. 안 좋다");
                        String hitbox = sc.nextLine();
                        if (hitbox.equals("1")) {
                            System.out.println("좋은 포지션으로 압박하며 일반 스킬들로 상대의 주요 스킬을 소모시키고, 주요 스킬로 진입해서 기회를 잡는다.");
                        } else if (hitbox.equals("2")) {
                            System.out.println("좋은 포지션을 유지하며 주요 스킬로 진입해서 기회를 잡는다.");
                        }
                    } else if (position.equals("2")) {
                        System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                        System.out.println("1. 좋다 2. 안 좋다");
                        String hitbox = sc.nextLine();
                        if (hitbox.equals("1")) {
                            System.out.println("안 좋은 포지션에서 벗어나는 데에 집중하며 상위 포지션에서 일반 스킬들로 상대의 주요 스킬을 소모시키고, 주요 스킬로 진입해서 기회를 잡는다.");
                        } else if (hitbox.equals("2")) {
                            System.out.println("안 좋은 포지션에서 벗어나는 데에 집중하며 상위 포지션에서 주요 스킬로 진입해서 기회를 잡는다.");
                        }
                    }
                } else if (mySkillCooldown.equals("3")) {
                    System.out.println("내 캐릭터의 포지션은 어떤가?");
                    System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                    String position = sc.nextLine();
                    if (position.equals("1")) {
                        System.out.println("좋은 포지션으로 압박하며 일반 스킬들로 상대의 주요 스킬을 소모시키고, 주요 스킬의 쿨타임을 기다렸다가 진입해서 기회를 잡는다.");
                    } else if (position.equals("2")) {
                        System.out.println("안 좋은 포지션에서 벗어나는 데에 집중하며 상위 포지션에서 일반 스킬들로 상대의 주요 스킬을 소모시키고, 주요 스킬의 쿨타임을 기다렸다가 진입해서 기회를 잡는다.");
                    }
                }
            } else if (opponentMainSkillCooldown.equals("3")) {
                System.out.println("내 캐릭터의 주요 스킬 중 몇 개가 쿨타임인가?");
                System.out.println("1. 1개 이하 2. 2개");
                String mySkillCooldown = sc.nextLine();
                if (mySkillCooldown.equals("1")) {
                    System.out.println("내 캐릭터의 포지션은 어떤가?");
                    System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                    String position = sc.nextLine();
                    if (position.equals("1")) {
                        System.out.println("좋은 포지션을 유지하며 주요 스킬로 진입해서 기회를 잡는다.");
                    } else if (position.equals("2")) {
                        System.out.println("안 좋은 포지션에서 빠르게 벗어나 주요 스킬로 진입해서 기회를 잡는다.");
                    }
                } else if(mySkillCooldown.equals("2")) {
                    System.out.println("내 캐릭터의 포지션은 어떤가?");
                    System.out.println("1.상대방보다 좋은 포지션이다 2.상대방보다 안 좋은 포지션이다");
                    String position = sc.nextLine();
                    if (position.equals("1")) {
                        System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                        System.out.println("1. 좋다 2. 안 좋다");
                        String hitbox = sc.nextLine();
                        if (hitbox.equals("1")) {
                            System.out.println("좋은 포지션을 유지하며 일반 스킬들로 진입해서 기회를 잡는다.");
                        } else if (hitbox.equals("2")) {
                            System.out.println("좋은 포지션을 유지하며 주요 스킬의 쿨타임을 기다렸다가 주요 스킬로 진입해서 기회를 잡는다.");
                        }
                    } else if (position.equals("2")) {
                        System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                        System.out.println("1. 좋다 2. 안 좋다");
                        String hitbox = sc.nextLine();
                        if (hitbox.equals("1")) {
                            System.out.println("안 좋은 포지션에서 빠르게 벗어나 일반 스킬들로 진입해서 기회를 잡는다.");
                        } else if (hitbox.equals("2")) {
                            System.out.println("안 좋은 포지션으로부터 벗어나는 데에 집중하며 주요 스킬의 쿨타임을 기다렸다가 주요 스킬로 진입해서 기회를 잡는다.");
                        }
                    }
                }
            }
        }
    }
}